package com.central.marksix.service.impl;

import com.central.common.model.SiteAdvertise;
import com.central.common.model.enums.SiteAdPositionEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SiteAdvertiseMapper;
import com.central.marksix.service.ISiteAdvertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SiteAdvertiseServiceImpl extends SuperServiceImpl<SiteAdvertiseMapper, SiteAdvertise> implements ISiteAdvertiseService {


    @Async
    @Override
    public void addHits(Long adId) {
        this.lambdaUpdate().setSql(" `hits` = `hits` + 1 ").eq(SiteAdvertise::getId, adId).update();
    }

    @Override
    public List<SiteAdvertise> getSiteAdvertise(Long sid, String deviceType, Integer position) {
        //非专题广告
        if (!SiteAdPositionEnum.isTopicAd(position)) {
            return this.lambdaQuery()
                    .eq(SiteAdvertise::getSiteId, sid)
                    .eq(SiteAdvertise::getStatus, true)
                    .eq(SiteAdvertise::getDevice, deviceType.toUpperCase())
                    .eq(SiteAdvertise::getPosition, position)
//                .ne(SiteAdvertise::getPosition, SiteAdPositionEnum.TOPIC.getCode())
                    .le(SiteAdvertise::getStartTime, new Date())
                    .ge(SiteAdvertise::getEndTime, new Date())
                    .orderByDesc(SiteAdvertise::getSort)
                    .orderByDesc(SiteAdvertise::getCreateTime)
                    .list();
        }
        return null;
    }


}
