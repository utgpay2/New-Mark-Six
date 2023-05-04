package com.central.porn.service;

import com.central.common.model.KpnSiteServe;
import com.central.common.service.ISuperService;

import java.util.List;

/**
 * 站点客服配置
 */
public interface IKpnSiteServeService extends ISuperService<KpnSiteServe> {

    /**
     * 获取站点客服信息
     * @param sid 站点id
     * @return
     */
    List<KpnSiteServe> getBySid(Long sid);
}

