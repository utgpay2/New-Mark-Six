package com.central.porn.service;

import com.central.common.model.KpnSiteUserMovieHistory;
import com.central.common.service.ISuperService;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;


public interface IKpnSiteUserMovieHistoryService extends ISuperService<KpnSiteUserMovieHistory> {

    /**
     * 获取会员浏览记录
     *
     * @param sid      站点id
     * @param userId   会员id
     * @param currPage 当前页
     * @param pageSize 每页条数
     * @return
     */
    PornPageResult<KpnSiteMovieBaseVo> getWatchHistoryByPage(Long sid, Long userId, Integer currPage, Integer pageSize);

    /**
     * 查询收藏影片
     *
     * @param userId  会员id
     * @param movieId 影片id
     * @return
     */
    KpnSiteUserMovieHistory getUserMovieHistory(Long userId, Long movieId);

    /**
     * 添加l浏览历史
     *
     * @param userId  会员id
     * @param movieId 影片id
     */
    void addUserMovieHistory(Long userId, Long movieId);

}
