package com.central.porn.service;

import com.central.common.model.pay.KpnSiteBankCard;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteBankCardPayVo;

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

