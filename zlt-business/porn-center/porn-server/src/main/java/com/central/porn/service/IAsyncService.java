package com.central.porn.service;

/**
 * 异步接口
 */
public interface IAsyncService {

    /**
     * 会员收藏演员
     *
     * @param sid     站点id
     * @param actorId 演员id
     */
    void addSiteActorFavorites(Long sid, Long actorId);

    /**
     * 会员取消演员收藏
     *
     * @param sid     站点id
     * @param actorId 演员id
     */
    void removeSiteActorFavorites(Long sid, Long actorId);

}
