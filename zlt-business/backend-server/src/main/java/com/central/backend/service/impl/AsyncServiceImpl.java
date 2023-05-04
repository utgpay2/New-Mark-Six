package com.central.backend.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnMovieTagService;
import com.central.backend.service.IKpnSiteMovieService;
import com.central.backend.service.IKpnSiteService;
import com.central.common.KpnMovieTag;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.model.KpnSiteMovie;
import com.central.common.redis.template.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {
    @Autowired
    @Lazy
    private IKpnMovieTagService movieTagService;

    @Autowired
    @Lazy
    private IKpnSiteService siteService;

    @Autowired
    @Lazy
    private IKpnSiteMovieService siteMovieService;

    @Async
    @Override
    public void setVipExpire(Date newVipExpire, Long userId) {
        try {
            long expireInSeconds = (newVipExpire.getTime() - new Date().getTime()) / 1000;
            String vipExpireKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_VIP_EXPIRE, userId);
            RedisRepository.setExpire(vipExpireKey, DateUtil.formatDateTime(newVipExpire), expireInSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void delActorCache(Long actorId) {
        try {
            RedisRepository.delete(StrUtil.format(PornConstants.RedisKey.KPN_ACTOR_KEY, actorId));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteTopicCache(Long sid) {
        try {
            RedisRepository.delete(StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_KEY, sid));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteTopicMovieCache(Long sid, Long topicId) {
        try {
            RedisRepository.delete(StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_SORT_KEY, sid, topicId));
            RedisRepository.delete(StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_DURATION, sid, topicId));
            RedisRepository.delete(StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_LATEST, sid, topicId));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteInfoCache(Long sid) {
        try {
            RedisRepository.delete(PornConstants.RedisKey.KPN_SITE_LIST_KEY);
            if (ObjectUtil.isNotEmpty(sid)) {
                RedisRepository.delete(StrUtil.format(PornConstants.RedisKey.KPN_SITE_INFO_KEY, sid));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteLinesCache() {
        try {
            RedisRepository.delete(PornConstants.RedisKey.KPN_LINE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteMovieVoCacheByTag(Long tagId) {
        try {
            if (ObjectUtil.isNotEmpty(tagId)) {
                List<KpnSite> kpnSites = siteService.getList();
                for (KpnSite kpnSite : kpnSites) {
                    Long sid = kpnSite.getId();
                    List<KpnMovieTag> kpnMovieTags = movieTagService.lambdaQuery().select(KpnMovieTag::getMovieId).eq(KpnMovieTag::getTagId, tagId).list();
                    for (KpnMovieTag kpnMovieTag : kpnMovieTags) {
                        String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITEID_MOVIEID_VO_KEY, sid, kpnMovieTag.getMovieId());
                        RedisRepository.delete(redisKey);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteMovieVoCacheById(List<Long> siteMovieIds) {
        try {
            if (CollUtil.isNotEmpty(siteMovieIds)) {
                List<KpnSiteMovie> siteMovies = siteMovieService.listByIds(siteMovieIds);
                for (KpnSiteMovie siteMovie : siteMovies) {
                    String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITEID_MOVIEID_VO_KEY, siteMovie.getSiteId(), siteMovie.getMovieId());
                    RedisRepository.delete(redisKey);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteActorMovieNumCache(List<Long> siteMovieIds) {
        try {
            if (CollUtil.isNotEmpty(siteMovieIds)) {
                List<KpnSiteMovie> siteMovies = siteMovieService.listByIds(siteMovieIds);
                for (KpnSiteMovie siteMovie : siteMovies) {
                    String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ACTOR_MOVIENUM_KEY, siteMovie.getSiteId(), siteMovie.getActorId());
                    RedisRepository.delete(redisKey);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSitePlatformCache(Long sid) {
        try {
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_PLATFORM_CONFIG_KEY, sid);
            RedisRepository.delete(redisKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteSignConfigCache(Long sid) {
        try {
            String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_SIGN_CONFIG, sid);
            RedisRepository.delete(redisKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void openSiteMoviesChangeSwitch(Long sid) {
        try {
            if (ObjectUtil.isNotEmpty(sid)) {
                String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_MOVIE_CHANGE_FLAG, sid);
                RedisRepository.set(redisKey, PornConstants.Numeric.OPEN);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }
}
