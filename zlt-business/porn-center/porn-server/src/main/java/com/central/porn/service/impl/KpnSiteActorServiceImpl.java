package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.language.LanguageUtil;
import com.central.common.model.KpnActor;
import com.central.common.model.KpnSiteActor;
import com.central.common.model.KpnSiteUserActorFavorites;
import com.central.common.redis.lock.RedissLockUtil;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnActorVo;
import com.central.porn.mapper.KpnSiteActorMapper;
import com.central.porn.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RefreshScope
@Service
public class KpnSiteActorServiceImpl extends SuperServiceImpl<KpnSiteActorMapper, KpnSiteActor> implements IKpnSiteActorService {

    @Autowired
    private IAsyncService asyncService;

    @Autowired
    private IKpnSiteUserActorFavoritesService userActorFavoritesService;

    @Autowired
    private IKpnActorService actorService;

    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Resource
    private TaskExecutor taskExecutor;

    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;

    @Override
    public KpnActorVo getKpnActorVo(Long sid, Long actorId) {
        //获取影片演员信息
        KpnActor kpnActor = actorService.getActorByIds(Collections.singletonList(actorId)).get(0);
        KpnActorVo kpnActorVo = new KpnActorVo();
        BeanUtil.copyProperties(kpnActor, kpnActorVo);
        kpnActorVo.setName(LanguageUtil.getLanguageName(kpnActorVo));
        if (StrUtil.isNotBlank(kpnActorVo.getAvatarUrl())) {
            kpnActorVo.setAvatarUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + kpnActorVo.getAvatarUrl());
        }
        if (StrUtil.isNotBlank(kpnActorVo.getPortraitUrl())) {
            kpnActorVo.setPortraitUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + kpnActorVo.getPortraitUrl());
        }
        //站点演员收藏量
        Long siteActorFavorites = getSiteActorFavorites(sid, kpnActor.getId());
        kpnActorVo.setFavorites(siteActorFavorites);
        //站点演员影片数量
        Long siteActorMovieNum = siteMovieService.getSiteActorMovieNum(sid, actorId);
        kpnActorVo.setMovieNum(siteActorMovieNum);

        return kpnActorVo;
    }

    @Override
    public Long getSiteActorFavorites(Long sid, Long actorId) {
        String siteActorFavoritesKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ACTOR_FAVORITES_KEY, sid, actorId);
        cacheSiteActorFavorites(siteActorFavoritesKey, sid, actorId);

        long favorites = RedisRepository.incr(siteActorFavoritesKey);
        taskExecutor.execute(() -> RedisRepository.decr(siteActorFavoritesKey));

        return favorites - 1;
    }

    @Override
    public Long addSiteActorFavorites(Long sid, Long userId, Long actorId) {
        KpnSiteUserActorFavorites userActorFavorites = userActorFavoritesService.getUserActorFavorites(userId, actorId);
        if (ObjectUtil.isNotEmpty(userActorFavorites)) {
            throw new RuntimeException("已经收藏,不可重复操作");
        }

        String siteActorFavoritesKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ACTOR_FAVORITES_KEY, sid, actorId);
        cacheSiteActorFavorites(siteActorFavoritesKey, sid, actorId);

        asyncService.addSiteActorFavorites(sid, actorId);
        actorService.addActorFavorites(actorId);
        userActorFavoritesService.add(userId, actorId);
        return RedisRepository.incr(siteActorFavoritesKey);
    }

    @Override
    public Long removeSiteActorFavorites(Long sid, Long userId, Long actorId) {
        KpnSiteUserActorFavorites userActorFavorites = userActorFavoritesService.getUserActorFavorites(userId, actorId);
        if (ObjectUtil.isEmpty(userActorFavorites)) {
            throw new RuntimeException("未收藏该演员,不可操作");
        }

        //获取站点收藏量
        String siteActorFavoritesKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ACTOR_FAVORITES_KEY, sid, actorId);
        cacheSiteActorFavorites(siteActorFavoritesKey, sid, actorId);

        asyncService.removeSiteActorFavorites(sid, actorId);
        actorService.removeActorFavorites(actorId);
        userActorFavoritesService.remove(userId, actorId);

        return RedisRepository.decr(siteActorFavoritesKey);
    }

    @Override
    public PornPageResult<KpnActorVo> getActorListByFavorites(Long sid, String sortOrder, Integer currPage, Integer pageSize) {
        Integer startIndex = (currPage - 1) * pageSize;
        List<Long> actorIds = baseMapper.getActorListByFavorites(sid, sortOrder, startIndex, pageSize);
        Long total = baseMapper.getActorCount(sid);
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        List<KpnActorVo> resultActorVos = new ArrayList<>();
        List<KpnActor> kpnActors = actorService.getActorByIds(actorIds);
        for (KpnActor kpnActor : kpnActors) {
            KpnActorVo kpnActorVo = new KpnActorVo();
            BeanUtil.copyProperties(kpnActor, kpnActorVo);
            kpnActorVo.setName(LanguageUtil.getLanguageName(kpnActorVo));
            if (StrUtil.isNotBlank(kpnActorVo.getAvatarUrl())) {
                kpnActorVo.setAvatarUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + kpnActorVo.getAvatarUrl());
            }
            if (StrUtil.isNotBlank(kpnActorVo.getPortraitUrl())) {
                kpnActorVo.setPortraitUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + kpnActorVo.getPortraitUrl());
            }
            resultActorVos.add(kpnActorVo);
        }
        return PornPageResult.<KpnActorVo>builder().count(total).totalPage(totalPage).currPage(currPage).pageSize(pageSize).data(resultActorVos).build();
    }

    @Override
    public PornPageResult<KpnActorVo> getActorListByCreateTime(Long sid, String sortOrder, Integer currPage, Integer pageSize) {
        Integer startIndex = (currPage - 1) * pageSize;
        List<Long> actorIds = baseMapper.getActorListByCreateTime(sid, sortOrder, startIndex, pageSize);
        Long total = baseMapper.getActorCount(sid);
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        List<KpnActorVo> resultActorVos = new ArrayList<>();
        List<KpnActor> kpnActors = actorService.getActorByIds(actorIds);
        for (KpnActor kpnActor : kpnActors) {
            KpnActorVo kpnActorVo = new KpnActorVo();
            BeanUtil.copyProperties(kpnActor, kpnActorVo);
            kpnActorVo.setName(LanguageUtil.getLanguageName(kpnActorVo));
            resultActorVos.add(kpnActorVo);
        }
        return PornPageResult.<KpnActorVo>builder().count(total).totalPage(totalPage).currPage(currPage).pageSize(pageSize).data(resultActorVos).build();
    }

    private void cacheSiteActorFavorites(String siteActorFavoritesKey, Long sid, Long actorId) {
        Object siteActorFavoritesObj = RedisRepository.get(siteActorFavoritesKey);

        if (ObjectUtil.isEmpty(siteActorFavoritesObj)) {
            String lockKey = StrUtil.format("Lock:" + siteActorFavoritesKey, sid, actorId);
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, PornConstants.Lock.WAIT_TIME, PornConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                throw new RuntimeException("加锁失败");
            }
            try {
                siteActorFavoritesObj = RedisRepository.get(siteActorFavoritesKey);
                if (ObjectUtil.isEmpty(siteActorFavoritesObj)) {
                    KpnSiteActor siteActor = this.lambdaQuery()
                            .eq(KpnSiteActor::getSiteId, sid)
                            .eq(KpnSiteActor::getActorId, actorId)
                            .one();
                    Long favorites = 0L;
                    if (ObjectUtil.isEmpty(siteActor)) {
                        KpnSiteActor newKpnSiteActor = KpnSiteActor.builder().siteId(sid).actorId(actorId).favorites(favorites).build();
                        save(newKpnSiteActor);
                    } else {
                        favorites = siteActor.getFavorites();
                    }
                    RedisRepository.setExpire(siteActorFavoritesKey, favorites, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                RedissLockUtil.unlock(lockKey);
            }
        }
    }
}
