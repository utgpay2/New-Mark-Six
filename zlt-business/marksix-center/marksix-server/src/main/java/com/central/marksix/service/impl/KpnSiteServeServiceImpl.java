package com.central.marksix.service.impl;

import com.central.common.model.KpnSiteServe;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.KpnSiteServeMapper;
import com.central.marksix.service.IKpnSiteServeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 站点客服配置
 */
@Slf4j
@Service
public class KpnSiteServeServiceImpl extends SuperServiceImpl<KpnSiteServeMapper, KpnSiteServe> implements IKpnSiteServeService {

    @Override
    public List<KpnSiteServe> getBySid(Long sid) {
        List<KpnSiteServe> siteServes = this.lambdaQuery().eq(KpnSiteServe::getSiteId, sid).eq(KpnSiteServe::getStatus, Boolean.TRUE).list();

        return siteServes;
    }
}
