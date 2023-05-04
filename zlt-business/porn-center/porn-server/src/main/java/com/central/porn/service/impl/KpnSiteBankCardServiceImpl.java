package com.central.porn.service.impl;

import com.central.common.model.pay.KpnSiteBankCard;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnSiteBankCardMapper;
import com.central.porn.service.IKpnSiteBankCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class KpnSiteBankCardServiceImpl extends SuperServiceImpl<KpnSiteBankCardMapper, KpnSiteBankCard> implements IKpnSiteBankCardService {

    @Override
    public List<KpnSiteBankCard> getBySiteId(Long siteId) {
        List<KpnSiteBankCard> list = this.lambdaQuery()
                .eq(KpnSiteBankCard::getSiteId, siteId)
                .eq(KpnSiteBankCard::getStatus, true)
                .orderByDesc(KpnSiteBankCard::getSort, KpnSiteBankCard::getId)
                .list();
        return list;
    }
}
