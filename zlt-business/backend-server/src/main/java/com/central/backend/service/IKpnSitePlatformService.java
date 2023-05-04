package com.central.backend.service;

import com.central.common.model.KpnSitePlatform;
import com.central.common.service.ISuperService;

public interface IKpnSitePlatformService extends ISuperService<KpnSitePlatform> {

    KpnSitePlatform findPromotionInfo(Long siteId);


    Boolean saveOrUpdatePlatform( KpnSitePlatform info) ;

}
