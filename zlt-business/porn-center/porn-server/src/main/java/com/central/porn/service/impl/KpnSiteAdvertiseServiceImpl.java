package com.central.porn.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSiteAdvertise;
import com.central.common.model.enums.SiteAdPositionEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnSiteAdvertiseMapper;
import com.central.porn.service.IKpnSiteAdvertiseService;
import com.central.porn.service.IKpnSiteTopicService;
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

    @Autowired
    private IKpnSiteTopicService siteTopicService;

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

        //// 专题广告
        //主题个数为0
        int topicSize = siteTopicService.getBySiteId(sid).size();
        if (topicSize == 0) {
            return new ArrayList<>();
        }

        //专题广告集合
        List<KpnSiteAdvertise> topicAdvertises = this.lambdaQuery()
                .eq(KpnSiteAdvertise::getSiteId, sid)
                .eq(KpnSiteAdvertise::getStatus, true)
                .eq(KpnSiteAdvertise::getDevice, deviceType)
                .eq(KpnSiteAdvertise::getPosition, SiteAdPositionEnum.TOPIC.getCode())
                .le(KpnSiteAdvertise::getStartTime, new Date())
                .ge(KpnSiteAdvertise::getEndTime, new Date())
                .orderByDesc(KpnSiteAdvertise::getSort)
                .orderByDesc(KpnSiteAdvertise::getCreateTime)
                .last(PornConstants.Sql.LIMIT_EMPTY + topicSize)
                .list();

        if (CollUtil.isEmpty(topicAdvertises)) {
            return new ArrayList<>();
        }

        List<KpnSiteAdvertise> topicResultAds = new ArrayList<>();
        if (topicAdvertises.size() >= topicSize) {
            for (KpnSiteAdvertise topicAdvertise : topicAdvertises) {
                if (topicResultAds.size() >= topicSize) {
                    break;
                }
                topicResultAds.add(topicAdvertise);
            }
            return topicResultAds;
        } else {
            while (true) {
                for (KpnSiteAdvertise topicAdvertise : topicAdvertises) {
                    topicResultAds.add(topicAdvertise);
                    if (topicResultAds.size() >= topicSize) {
                        return topicResultAds;
                    }
                }
            }
        }
    }


}
