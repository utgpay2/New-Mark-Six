package com.central.porn.jobs;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.model.KpnSiteMovie;
import com.central.common.model.enums.SiteMovieStatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.porn.service.IKpnSiteMovieService;
import com.central.porn.service.IKpnSiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CacheMovieNamesJob implements CommandLineRunner {

    @Autowired
    private IKpnSiteService siteService;

    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void loadCacheJob() {
        log.info("check site movie change work is running ....");

        List<KpnSite> kpnSites = siteService.getList();
        for (KpnSite kpnSite : kpnSites) {
            Long sid = kpnSite.getId();

            String redisFlagKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_CHANGE_FLAG, sid);
            Integer oper = (Integer) RedisRepository.get(redisFlagKey);
            if (oper != null && oper.equals(PornConstants.Numeric.OPEN) ) {
                cacheData();
                reCacheSiteData(sid);
                log.info("sid:{},数据同步完成!", sid);
                sleep();
            }
            RedisRepository.set(redisFlagKey, PornConstants.Numeric.CLOSE);
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        cacheData();
        List<KpnSite> kpnSites = siteService.getList();
        for (KpnSite kpnSite : kpnSites) {
            Long sid = kpnSite.getId();
            reCacheSiteData(sid);
            log.info("sid:{},数据同步完成!", sid);

            String redisFlagKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_CHANGE_FLAG, sid);
            RedisRepository.set(redisFlagKey, PornConstants.Numeric.CLOSE);
        }
    }

    private void cacheData() {
        PornConstants.LocalCache.LOCAL_MAP_MOVIE_NAME.clear();
        List<KpnSiteMovie> kpnSiteMovies = siteMovieService.lambdaQuery()
                .select(KpnSiteMovie::getMovieId, KpnSiteMovie::getNameZh, KpnSiteMovie::getNameEn, KpnSiteMovie::getNameKh)
                .eq(KpnSiteMovie::getStatus, SiteMovieStatusEnum.ON_SHELF.getStatus())
                .groupBy(KpnSiteMovie::getMovieId)
                .list();

        Map<Long, String[]> collect = kpnSiteMovies.stream().collect(Collectors.toMap(KpnSiteMovie::getMovieId,
                kpnSiteMovie -> new String[]{kpnSiteMovie.getNameZh(), kpnSiteMovie.getNameEn(), kpnSiteMovie.getNameKh()}, (s1, s2) -> s2));
        PornConstants.LocalCache.LOCAL_MAP_MOVIE_NAME.putAll(collect);

    }

    private void reCacheSiteData(Long sid) {
        PornConstants.LocalCache.LOCAL_MAP_SITE_MOVIE_IDS.remove(sid);
        List<KpnSiteMovie> kpnSiteMovies = siteMovieService.lambdaQuery()
                .select(KpnSiteMovie::getId, KpnSiteMovie::getMovieId, KpnSiteMovie::getVv, KpnSiteMovie::getDuration, KpnSiteMovie::getCreateTime)
                .eq(KpnSiteMovie::getSiteId, sid)
                .eq(KpnSiteMovie::getStatus, SiteMovieStatusEnum.ON_SHELF.getStatus())
                .list();

        if (CollectionUtil.isEmpty(kpnSiteMovies)) {
            return;
        }

        List<Long> movieIds = kpnSiteMovies.stream().map(KpnSiteMovie::getMovieId).collect(Collectors.toList());
        PornConstants.LocalCache.LOCAL_MAP_SITE_MOVIE_IDS.put(sid, movieIds);

        //影片上架时间排序缓存redis
        String siteAllLatestRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ALL_MOVIEID_LATEST, sid);
        List<String> siteAllLatestMovieIds = kpnSiteMovies.stream()
                .sorted(Comparator.comparing(KpnSiteMovie::getCreateTime).thenComparing(KpnSiteMovie::getId))
                .map(kpnSiteMovie -> String.valueOf(kpnSiteMovie.getMovieId()))
                .collect(Collectors.toList());

        RedisRepository.delete(siteAllLatestRedisKey);
        RedisRepository.leftPushAll(siteAllLatestRedisKey, siteAllLatestMovieIds);
    }


}
