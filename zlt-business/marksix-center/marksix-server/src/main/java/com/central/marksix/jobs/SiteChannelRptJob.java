//package com.central.marksix.jobs;
//
//import cn.hutool.core.collection.CollectionUtil;
//import cn.hutool.core.util.StrUtil;
//import com.central.common.constant.MarksixConstants;
//import com.central.common.model.Site;
//import com.central.common.redis.template.RedisRepository;
//import com.central.marksix.service.IKpnSiteChannelService;
//import com.central.marksix.service.ISiteService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.job.api.ShardingContext;
//import org.apache.shardingsphere.job.simple.job.SimpleJob;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//public class SiteChannelRptJob implements SimpleJob, CommandLineRunner {
//
//    @Autowired
//    private ISiteService siteService;
//
//    @Autowired
//    private IKpnSiteChannelService siteChannelService;
//
//    @Override
//    public void execute(ShardingContext shardingContext) {
//        log.info("SiteChannelRptJob -> params:{}, time:{}", shardingContext.getJobParameter(), LocalDateTime.now());
//
//        cache();
//    }
//
//    private void cache() {
//        try {
//            List<Site> kpnSites = siteService.getList();
//            for (Site kpnSite : kpnSites) {
//                Long sid = kpnSite.getId();
//
//                //非固定标签
//                List<Long> channelIds = siteChannelService.getSiteNotStableChannelIds(sid);
//                for (Long channelId : channelIds) {
//                    //按播放量高->低
//                    String redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_CHANNEL_MOVIEID_VV, sid, channelId);
//                    List<Long> movieIdsByVvDesc = siteChannelService.getChannelMovieIdsSortedByColumn(sid, channelId, MarksixConstants.Sql.COLUMN_VV);
//                    RedisRepository.delete(redisKey);
//                    if (CollectionUtil.isNotEmpty(movieIdsByVvDesc)) {
//                        RedisRepository.leftPushAll(redisKey, movieIdsByVvDesc.stream().map(String::valueOf).collect(Collectors.toList()));
//                    }
//
//                    //按影片时长高->低
//                    redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_CHANNEL_MOVIEID_DURATION, sid, channelId);
//                    List<Long> movieIdsByDuration = siteChannelService.getChannelMovieIdsSortedByColumn(sid, channelId, MarksixConstants.Sql.COLUMN_DURATION);
//                    RedisRepository.delete(redisKey);
//                    if (CollectionUtil.isNotEmpty(movieIdsByDuration)) {
//                        RedisRepository.leftPushAll(redisKey, movieIdsByDuration.stream().map(String::valueOf).collect(Collectors.toList()));
//                    }
//
//                    //按影片创建时间新->旧
//                    redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_CHANNEL_MOVIEID_LATEST, sid, channelId);
//                    List<Long> movieIdsByCreateTime = siteChannelService.getChannelMovieIdsSortedByColumn(sid, channelId, MarksixConstants.Sql.COLUMN_CREATE_TIME);
//                    RedisRepository.delete(redisKey);
//                    if (CollectionUtil.isNotEmpty(movieIdsByCreateTime)) {
//                        RedisRepository.leftPushAll(redisKey, movieIdsByCreateTime.stream().map(String::valueOf).collect(Collectors.toList()));
//                    }
//                }
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        cache();
//    }
//}
