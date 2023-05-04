package com.central.porn.service;

import com.central.common.model.KpnSiteUserMovieFavorites;
import com.central.common.service.ISuperService;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;


public interface IKpnSiteUserMovieFavoritesService extends ISuperService<KpnSiteUserMovieFavorites> {

    /**
     * 查询收藏影片信息
     *
     * @param userId  会员id
     * @param movieId 影片id
     * @return
     */
    KpnSiteUserMovieFavorites getUserMovieFavorites(Long userId, Long movieId);

    /**
     * 添加收藏
     *
     * @param userId  会员id
     * @param movieId 影片id
     */
    void add(Long userId, Long movieId);

    /**
     * 取消收藏
     *
     * @param userId  会员id
     * @param movieId 影片id
     */
    void remove(Long userId, Long movieId);

    /**
     * 获取收藏影片
     *
     * @param sid      站点id
     * @param userId   会员id
     * @param currPage 当前页数
     * @param pageSize 每页条数
     * @return
     */
    PornPageResult<KpnSiteMovieBaseVo> getFavoritesMoviesByPage(Long sid, Long userId, Integer currPage, Integer pageSize);
}
