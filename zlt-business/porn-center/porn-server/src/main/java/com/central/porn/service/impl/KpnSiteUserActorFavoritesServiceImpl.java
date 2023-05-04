package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.KpnActor;
import com.central.common.model.KpnSiteUserActorFavorites;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnActorVo;
import com.central.porn.mapper.KpnSiteUserActorFavoritesMapper;
import com.central.porn.service.IKpnActorService;
import com.central.porn.service.IKpnSiteUserActorFavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteUserActorFavoritesServiceImpl extends SuperServiceImpl<KpnSiteUserActorFavoritesMapper, KpnSiteUserActorFavorites> implements IKpnSiteUserActorFavoritesService {

    @Autowired
    private IKpnActorService actorService;

    @Override
    public KpnSiteUserActorFavorites getUserActorFavorites(Long userId, Long actorId) {
        return this.lambdaQuery()
                .eq(KpnSiteUserActorFavorites::getUserId, userId)
                .eq(KpnSiteUserActorFavorites::getActorId, actorId)
                .one();
    }

    @Async
    @Override
    public void add(Long userId, Long actorId) {
        KpnSiteUserActorFavorites userActorFavorites = KpnSiteUserActorFavorites.builder().userId(userId).actorId(actorId).build();
        save(userActorFavorites);
    }

    @Async
    @Override
    public void remove(Long userId, Long actorId) {
        this.lambdaUpdate()
                .eq(KpnSiteUserActorFavorites::getUserId, userId)
                .eq(KpnSiteUserActorFavorites::getActorId, actorId)
                .remove();
    }

    @Override
    public PornPageResult<KpnActorVo> getFavoritesActorsByPage(Long sid, Long userId, Integer currPage, Integer pageSize) {
        Page<KpnSiteUserActorFavorites> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<KpnSiteUserActorFavorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(KpnSiteUserActorFavorites::getActorId);
        wrapper.eq(KpnSiteUserActorFavorites::getUserId, userId);
        wrapper.orderByDesc(KpnSiteUserActorFavorites::getCreateTime);

        Page<KpnSiteUserActorFavorites> historyPage = this.baseMapper.selectPage(page, wrapper);
        Long total = page.getTotal();
        List<Long> actorIds = historyPage.getRecords().stream().map(KpnSiteUserActorFavorites::getActorId).collect(Collectors.toList());

        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        List<KpnActor> kpnActors = actorService.getActorByIds(actorIds);

        List<KpnActorVo> kpnActorVos = kpnActors.stream().map(kpnActor -> {
            KpnActorVo kpnActorVo = new KpnActorVo();
            BeanUtil.copyProperties(kpnActor, kpnActorVo);
            kpnActorVo.setName(LanguageUtil.getLanguageName(kpnActorVo));
            kpnActorVo.setHasFavor(null);
            return kpnActorVo;
        }).collect(Collectors.toList());

        return PornPageResult.<KpnActorVo>builder()
                .data(kpnActorVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }
}
























