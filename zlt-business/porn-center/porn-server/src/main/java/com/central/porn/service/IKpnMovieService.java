package com.central.porn.service;

import com.central.common.model.KpnMovie;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;

import java.util.List;


public interface IKpnMovieService extends ISuperService<KpnMovie> {

    /**
     * 影片总播放量统计
     *
     * @param movieId 影片id
     */
    void addMovieVv(Long movieId);

    /**
     * 影片总收藏量-加1
     *
     * @param movieId
     */
    void addMovieFavorites(Long movieId);

    /**
     * 影片总收藏量-减1
     *
     * @param movieId
     */
    void removeMovieFavorites(Long movieId);
}
