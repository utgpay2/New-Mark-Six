package com.central.porn.service;

import com.central.common.model.KpnSiteAdvertise;
import com.central.common.service.ISuperService;

import java.util.List;


public interface IKpnSiteAdvertiseService extends ISuperService<KpnSiteAdvertise> {

    /**
     * 获取站点广告
     *
     * @param sid 站点id
     * @param deviceType 设备类型
     * @param position 投放位置 1首页轮播图,2首页平台展示,3首页专题广告,4福利,5游戏轮播图,6游戏广告
     */
    List<KpnSiteAdvertise> getSiteAdvertise(Long sid, String deviceType, Integer position);

    /**
     * 增加广告点击量
     * @param adId
     */
    void addHits(Long adId);
}
