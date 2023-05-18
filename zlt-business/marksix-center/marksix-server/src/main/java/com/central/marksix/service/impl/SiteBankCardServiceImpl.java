package com.central.marksix.service.impl;

import com.central.common.model.pay.SiteBankCard;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SiteBankCardMapper;
import com.central.marksix.service.ISiteBankCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class SiteBankCardServiceImpl extends SuperServiceImpl<SiteBankCardMapper, SiteBankCard> implements ISiteBankCardService {

    @Override
    public List<SiteBankCard> getBySiteId(Long siteId) {
        List<SiteBankCard> list = this.lambdaQuery()
                .eq(SiteBankCard::getSiteId, siteId)
                .eq(SiteBankCard::getStatus, true)
                .orderByDesc(SiteBankCard::getSort, SiteBankCard::getId)
                .list();
        return list;
    }
}
