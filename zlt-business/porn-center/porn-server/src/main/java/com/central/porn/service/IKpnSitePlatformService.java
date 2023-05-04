package com.central.porn.service;

import com.central.common.model.KpnSitePlatform;
import com.central.common.service.ISuperService;


public interface IKpnSitePlatformService extends ISuperService<KpnSitePlatform> {

    /**
     * 获取站点平台配置
     *
     * @param sid 站点id
     * @return
     */
    KpnSitePlatform getBySiteId(Long sid);
}
