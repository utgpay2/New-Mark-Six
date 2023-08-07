package com.central.marksix.service;

import com.central.common.model.SiteServe;
import com.central.common.service.ISuperService;

import java.util.List;

/**
 * 商户客服配置
 */
public interface ISiteServeService extends ISuperService<SiteServe> {

    /**
     * 获取商户客服信息
     * @param sid 商户id
     * @return
     */
    List<SiteServe> getBySid(Long sid);
}

