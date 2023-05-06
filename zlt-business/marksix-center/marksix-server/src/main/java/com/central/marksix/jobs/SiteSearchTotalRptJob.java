//package com.central.marksix.jobs;
//
//import cn.hutool.core.util.StrUtil;
//import com.central.common.constant.MarksixConstants;
//import com.central.common.model.KpnSite;
//import com.central.common.redis.template.RedisRepository;
//import com.central.marksix.service.IKpnSiteService;
//import com.central.marksix.service.IRptSiteSearchTotalService;
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
//public class SiteSearchTotalRptJob implements SimpleJob, CommandLineRunner {
//
//    @Autowired
//    private IKpnSiteService siteService;
//
//    @Autowired
//    private IRptSiteSearchTotalService rptSiteSearchTotalService;
//
//    @Override
//    public void execute(ShardingContext shardingContext) {
//        log.info("SiteSearchTotalRpt -> params:{}, time:{}", shardingContext.getJobParameter(), LocalDateTime.now());
//        cache();
//    }
//
//    private void cache() {
//        try {
//            List<KpnSite> kpnSites = siteService.getList();
//
//            for (KpnSite kpnSite : kpnSites) {
//                Long sid = kpnSite.getId();
//                String redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_SEARCH_TOTAL_KEY, sid);
//                RedisRepository.delete(redisKey);
//                rptSiteSearchTotalService.getSiteSearchTotal(sid);
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
