package com.central.porn.jobs;

import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.redis.template.RedisRepository;
import com.central.porn.service.IKpnSiteService;
import com.central.porn.service.IRptSiteSearchDateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class SiteSearchWeekRptJob implements SimpleJob, CommandLineRunner {

    @Autowired
    private IKpnSiteService siteService;

    @Autowired
    private IRptSiteSearchDateService rptSiteSearchDateService;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("SiteSearchWeekRptJob -> params:{}, time:{}", shardingContext.getJobParameter(), LocalDateTime.now());
        cache();
    }

    private void cache() {
        try {
            List<KpnSite> kpnSites = siteService.getList();
            for (KpnSite kpnSite : kpnSites) {
                Long sid = kpnSite.getId();
                String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_SEARCH_WEEK_KEY, sid);
                RedisRepository.delete(redisKey);
                rptSiteSearchDateService.getSiteSearchWeek(sid);
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
