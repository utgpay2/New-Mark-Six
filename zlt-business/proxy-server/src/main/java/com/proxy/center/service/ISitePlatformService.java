package com.proxy.center.service;

import com.central.common.model.SitePlatform;
import com.central.common.service.ISuperService;

public interface ISitePlatformService extends ISuperService<SitePlatform> {

    SitePlatform findPromotionInfo(Long siteId);


    Boolean saveOrUpdatePlatform(SitePlatform info) ;

}
