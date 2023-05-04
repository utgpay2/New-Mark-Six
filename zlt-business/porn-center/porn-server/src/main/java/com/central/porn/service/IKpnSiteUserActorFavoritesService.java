package com.central.porn.service;

import com.central.common.model.KpnSiteUserActorFavorites;
import com.central.common.service.ISuperService;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnActorVo;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;


public interface IKpnSiteUserActorFavoritesService extends ISuperService<KpnSiteUserActorFavorites> {

    /**
     * 获取会员收藏的演员
     *
     * @param userId  会员id
     * @param actorId 演员id
     * @return
     */
    KpnSiteUserActorFavorites getUserActorFavorites(Long userId, Long actorId);

    /**
     * 增加收藏
     *
     * @param userId  会员id
     * @param actorId 演员id
     */
    void add(Long userId, Long actorId);

    /**
     * 移除收藏
     *
     * @param userId  会员id
     * @param actorId 演员id
     */
    void remove(Long userId, Long actorId);

    /**
     * 获取收藏演员
     *
     * @param siteId   站点id
     * @param userId   会员id
     * @param currPage 当前页
     * @param pageSize 每页条数
     * @return
     */
    PornPageResult<KpnActorVo> getFavoritesActorsByPage(Long siteId, Long userId, Integer currPage, Integer pageSize);
}
