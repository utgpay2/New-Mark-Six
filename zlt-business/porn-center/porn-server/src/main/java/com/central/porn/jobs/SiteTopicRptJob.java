package com.central.porn.jobs;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.redis.template.RedisRepository;
import com.central.porn.service.IKpnSiteService;
import com.central.porn.service.IKpnSiteTopicMovieService;
import com.central.porn.service.IKpnSiteTopicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SiteTopicRptJob implements SimpleJob, CommandLineRunner {

    @Autowired
    private IKpnSiteService siteService;

    @Autowired
    private IKpnSiteTopicMovieService siteTopicMovieService;

    @Autowired
    private IKpnSiteTopicService siteTopicService;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("SiteTopicRptJob -> params:{}, time:{}", shardingContext.getJobParameter(), LocalDateTime.now());

        cache();
    }

    private void cache() {
        try {
            List<KpnSite> kpnSites = siteService.getList();
            for (KpnSite kpnSite : kpnSites) {
                Long sid = kpnSite.getId();

                List<Long> topicIds = siteTopicService.getTopicIdsBySiteId(sid);
                for (Long topicId : topicIds) {
                    //按播放量高->低
                    String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_VV, sid, topicId);
                    List<Long> movieIdsByVvDesc = siteTopicMovieService.getTopicMovieIdsSortedByColumn(sid, topicId, PornConstants.Sql.COLUMN_VV);
                    RedisRepository.delete(redisKey);
                    if (CollectionUtil.isNotEmpty(movieIdsByVvDesc)) {
                        RedisRepository.leftPushAll(redisKey, movieIdsByVvDesc.stream().map(String::valueOf).collect(Collectors.toList()));
                    }

                    //按影片时长高->低
                    redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_DURATION, sid, topicId);
                    if (RedisRepository.length(redisKey) <= 0) {
                        List<Long> movieIdsByDuration = siteTopicMovieService.getTopicMovieIdsSortedByColumn(sid, topicId, PornConstants.Sql.COLUMN_DURATION);
                        if (CollectionUtil.isNotEmpty(movieIdsByDuration)) {
                            RedisRepository.leftPushAll(redisKey, movieIdsByDuration.stream().map(String::valueOf).collect(Collectors.toList()));
                        }
                    }
                    //按影片创建时间新->旧
                    redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_TOPIC_MOVIEID_LATEST, sid, topicId);
                    if (RedisRepository.length(redisKey) <= 0) {
                        List<Long> movieIdsByCreateTime = siteTopicMovieService.getTopicMovieIdsSortedByColumn(sid, topicId, PornConstants.Sql.COLUMN_CREATE_TIME);
                        if (CollectionUtil.isNotEmpty(movieIdsByCreateTime)) {
                            RedisRepository.leftPushAll(redisKey, movieIdsByCreateTime.stream().map(String::valueOf).collect(Collectors.toList()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        cache();
    }
}
