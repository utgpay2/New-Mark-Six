package com.central.marksix.service;

import com.central.common.model.SiteAdvertise;
import com.central.common.service.ISuperService;

import java.util.List;


public interface ISiteAdvertiseService extends ISuperService<SiteAdvertise> {

    /**
     * 获取商户广告
     *
     * @param sid 商户id
     * @param deviceType 设备类型
     * @param position 投放位置 1首页轮播图,2首页平台展示,3首页专题广告,4福利,5游戏轮播图,6游戏广告
     */
    List<SiteAdvertise> getSiteAdvertise(Long sid, String deviceType, Integer position);

    /**
     * 增加广告点击量
     * @param adId
     */
    void addHits(Long adId);
}
