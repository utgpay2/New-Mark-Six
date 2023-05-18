package com.central.backend.service;

import com.central.common.model.SitePromotion;
import com.central.common.service.ISuperService;

public interface ISitePromotionService extends ISuperService<SitePromotion> {




    SitePromotion findPromotionInfo(Long siteId);


    Boolean saveOrUpdatePromotion(SitePromotion info);
}
