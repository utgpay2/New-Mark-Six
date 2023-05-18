//package com.central.marksix.jobs;
//
//import cn.hutool.core.util.StrUtil;
//import com.central.common.constant.MarksixConstants;
//import com.central.common.model.Site;
//import com.central.common.redis.template.RedisRepository;
//import com.central.marksix.service.ISiteService;
//import com.central.marksix.service.IRptSiteSearchDateService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.job.api.ShardingContext;
//import org.apache.shardingsphere.job.simple.job.SimpleJob;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Slf4j
//@Component
//public class SiteSearchWeekRptJob implements SimpleJob, CommandLineRunner {
//
//    @Autowired
//    private ISiteService siteService;
//
//    @Autowired
//    private IRptSiteSearchDateService rptSiteSearchDateService;
//
//    @Override
//    public void execute(ShardingContext shardingContext) {
//        log.info("SiteSearchWeekRptJob -> params:{}, time:{}", shardingContext.getJobParameter(), LocalDateTime.now());
//        cache();
//    }
//
//    private void cache() {
//        try {
//            List<Site> kpnSites = siteService.getList();
//            for (Site kpnSite : kpnSites) {
//                Long sid = kpnSite.getId();
//                String redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_SEARCH_WEEK_KEY, sid);
//                RedisRepository.delete(redisKey);
//                rptSiteSearchDateService.getSiteSearchWeek(sid);
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
