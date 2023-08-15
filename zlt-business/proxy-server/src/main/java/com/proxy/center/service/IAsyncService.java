package com.proxy.center.service;

import java.util.Date;

public interface IAsyncService {

    /**
     * 设置最新过期时间
     *
     * @param newVipExpire 最新过期时间
     * @param userId       会员id
     */
    void setVipExpire(Date newVipExpire, Long userId);

    /**
     * 删除商户信息缓存
     *
     * @param sid 商户id
     */
    void deleteSiteInfoCache(Long sid);

    /**
     * 删除线路缓存
     */
    void deleteLinesCache();

    /**
     * 商户id
     *
     * @param sid 商户id
     */
    void deleteSitePlatformCache(Long sid);

    /**
     * 删除商户签到配置缓存
     *
     * @param sid 商户id
     */
    void deleteSiteSignConfigCache(Long sid);

}
