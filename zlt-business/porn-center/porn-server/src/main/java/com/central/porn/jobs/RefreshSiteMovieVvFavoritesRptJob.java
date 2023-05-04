package com.central.porn.jobs;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.model.KpnSiteMovie;
import com.central.common.redis.template.RedisRepository;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;
import com.central.porn.service.IKpnSiteMovieService;
import com.central.porn.service.IKpnSiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 刷新视频播放量/收藏量
 */
@Slf4j
@Component
public class RefreshSiteMovieVvFavoritesRptJob implements SimpleJob, CommandLineRunner {

    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Autowired
    private IKpnSiteService siteService;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("RefreshSiteMovieVvFavoritesRptJob -> params:{}, time:{}", shardingContext.getJobParameter(), LocalDateTime.now());
        cache();
    }

    private void cache() {
        try {
            List<KpnSite> kpnSites = siteService.getList();
            for (KpnSite kpnSite : kpnSites) {
                Long sid = kpnSite.getId();
                List<Long> movieIds = siteMovieService.getSiteMovieIds(sid);
                for (Long movieId : movieIds) {
                    String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITEID_MOVIEID_VO_KEY, sid, movieId);
                    KpnSiteMovieBaseVo movieBaseVo = (KpnSiteMovieBaseVo) RedisRepository.get(redisKey);
                    if (ObjectUtil.isEmpty(movieBaseVo)) {
                        movieBaseVo = siteMovieService.getSiteMovieByIds(sid, Collections.singletonList(movieId), true).get(0);
                    }
                    KpnSiteMovie siteMovieVvFavorites = siteMovieService.getSiteMovieVvFavorites(sid, movieId);
                    movieBaseVo.setVv(siteMovieVvFavorites.getVv());
                    movieBaseVo.setFavorites(siteMovieVvFavorites.getFavorites());
                    RedisRepository.setExpire(redisKey, movieBaseVo, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
                }

                //刷新站点最热
                movieIds = siteMovieService.getSiteMovieIdsOrderByVv(sid, false);
                if (CollectionUtil.isEmpty(movieIds)) {
                    continue;
                }
                String siteAllVvRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ALL_MOVIEID_VV, sid);
                RedisRepository.delete(siteAllVvRedisKey);
                if (CollectionUtil.isNotEmpty(movieIds)) {
                    RedisRepository.leftPushAll(siteAllVvRedisKey, movieIds.stream().map(String::valueOf).collect(Collectors.toList()));
                }

                //刷新站点VIP最热
                movieIds = siteMovieService.getSiteMovieIdsOrderByVv(sid, true);
                String siteVipVvRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_VIP_MOVIEID_VV, sid);
                RedisRepository.delete(siteVipVvRedisKey);
                if (CollectionUtil.isEmpty(movieIds)) {
                    continue;
                }
                RedisRepository.leftPushAll(siteVipVvRedisKey, movieIds.stream().map(String::valueOf).collect(Collectors.toList()));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            System.gc();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        cache();
    }
}
