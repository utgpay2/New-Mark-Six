package com.central.marksix.service.impl;

import com.central.common.model.SiteServe;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SiteServeMapper;
import com.central.marksix.service.ISiteServeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 商户客服配置
 */
@Slf4j
@Service
public class SiteServeServiceImpl extends SuperServiceImpl<SiteServeMapper, SiteServe> implements ISiteServeService {

    @Override
    public List<SiteServe> getBySid(Long sid) {
        List<SiteServe> siteServes = this.lambdaQuery().eq(SiteServe::getSiteId, sid).eq(SiteServe::getStatus, Boolean.TRUE).list();

        return siteServes;
    }
}
