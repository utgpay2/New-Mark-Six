package com.central.backend.service;

import java.util.Date;
import java.util.List;

public interface IAsyncService {

    /**
     * 设置最新过期时间
     *
     * @param newVipExpire 最新过期时间
     * @param userId       会员id
     */
    void setVipExpire(Date newVipExpire, Long userId);

    /**
     * 删除站点信息缓存
     *
     * @param sid 站点id
     */
    void deleteSiteInfoCache(Long sid);

    /**
     * 删除线路缓存
     */
    void deleteLinesCache();

    /**
     * 站点id
     *
     * @param sid 站点id
     */
    void deleteSitePlatformCache(Long sid);

    /**
     * 删除站点签到配置缓存
     *
     * @param sid 站点id
     */
    void deleteSiteSignConfigCache(Long sid);

}
