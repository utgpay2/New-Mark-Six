package com.central.marksix.service;

import com.central.common.model.SiteServe;
import com.central.common.service.ISuperService;

import java.util.List;

/**
 * 站点客服配置
 */
public interface ISiteServeService extends ISuperService<SiteServe> {

    /**
     * 获取站点客服信息
     * @param sid 站点id
     * @return
     */
    List<SiteServe> getBySid(Long sid);
}

