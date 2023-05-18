package com.central.marksix.service;

import com.central.common.model.SitePlatform;
import com.central.common.service.ISuperService;


public interface ISitePlatformService extends ISuperService<SitePlatform> {

    /**
     * 获取站点平台配置
     *
     * @param sid 站点id
     * @return
     */
    SitePlatform getBySiteId(Long sid);
}
