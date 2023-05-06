package com.central.marksix.service;

import com.central.common.model.pay.KpnSiteBankCard;
import com.central.common.service.ISuperService;

import java.util.List;

public interface IKpnSiteBankCardService extends ISuperService<KpnSiteBankCard> {

    /**
     * 获取站点支付卡
     *
     * @param siteId 站点id
     * @return
     */
    List<KpnSiteBankCard> getBySiteId(Long siteId);
}

