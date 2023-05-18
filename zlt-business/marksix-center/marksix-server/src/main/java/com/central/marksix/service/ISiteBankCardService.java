package com.central.marksix.service;

import com.central.common.model.pay.SiteBankCard;
import com.central.common.service.ISuperService;

import java.util.List;

public interface ISiteBankCardService extends ISuperService<SiteBankCard> {

    /**
     * 获取站点支付卡
     *
     * @param siteId 站点id
     * @return
     */
    List<SiteBankCard> getBySiteId(Long siteId);
}

