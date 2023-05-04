package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.central.common.model.KpnSiteServe;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.vo.KpnSiteServeVo;
import com.central.porn.mapper.KpnSiteServeMapper;
import com.central.porn.service.IKpnSiteServeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
