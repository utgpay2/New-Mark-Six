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
     * 删除演员cache
     *
     * @param actorId 演员id
     */
    void delActorCache(Long actorId);

    /**
     * 删除站点专题缓存
     *
     * @param sid 站点id
     */
    void deleteSiteTopicCache(Long sid);

    /**
     * 删除站点专题影片
     *
     * @param sid     站点id
     * @param topicId 站提id
     */
    void deleteSiteTopicMovieCache(Long sid, Long topicId);

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
     * 删除标签相关影片缓存
     *
     * @param tagId 标签id
     */
    void deleteSiteMovieVoCacheByTag(Long tagId);

    /**
     * 删除站点影片缓存
     *
     * @param siteMovieIds 站点影片id
     */
    void deleteSiteMovieVoCacheById(List<Long> siteMovieIds);

    /**
     * 删除站点演员影片数缓存
     *
     * @param siteMovieIds 站点影片id
     */
    void deleteSiteActorMovieNumCache(List<Long> siteMovieIds);

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

    /**
     * 开启同步开关
     *
     * @param sid 站点id
     */
    void openSiteMoviesChangeSwitch(Long sid);
}
