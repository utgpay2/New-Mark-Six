package com.central.marksix.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.KpnSiteAdvertise;
import com.central.common.model.enums.SiteAdPositionEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.KpnSiteAdvertiseMapper;
import com.central.marksix.service.IKpnSiteAdvertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class KpnSiteAdvertiseServiceImpl extends SuperServiceImpl<KpnSiteAdvertiseMapper, KpnSiteAdvertise> implements IKpnSiteAdvertiseService {


    @Async
    @Override
    public void addHits(Long adId) {
        this.lambdaUpdate().setSql(" `hits` = `hits` + 1 ").eq(KpnSiteAdvertise::getId, adId).update();
    }

    @Override
    public List<KpnSiteAdvertise> getSiteAdvertise(Long sid, String deviceType, Integer position) {
        //非专题广告
        if (!SiteAdPositionEnum.isTopicAd(position)) {
            return this.lambdaQuery()
                    .eq(KpnSiteAdvertise::getSiteId, sid)
                    .eq(KpnSiteAdvertise::getStatus, true)
                    .eq(KpnSiteAdvertise::getDevice, deviceType.toUpperCase())
                    .eq(KpnSiteAdvertise::getPosition, position)
//                .ne(KpnSiteAdvertise::getPosition, SiteAdPositionEnum.TOPIC.getCode())
                    .le(KpnSiteAdvertise::getStartTime, new Date())
                    .ge(KpnSiteAdvertise::getEndTime, new Date())
                    .orderByDesc(KpnSiteAdvertise::getSort)
                    .orderByDesc(KpnSiteAdvertise::getCreateTime)
                    .list();
        }
        return null;
    }


}
