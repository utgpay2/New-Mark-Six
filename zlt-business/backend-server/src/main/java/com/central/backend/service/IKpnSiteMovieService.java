package com.central.backend.service;

import com.central.backend.controller.movie.KpnSiteMovieVo;
import com.central.backend.controller.movie.QueryMovieCo;
import com.central.backend.model.dto.KpnSiteMoviePayTypeDto;
import com.central.backend.model.dto.KpnSiteMovieStatusDto;
import com.central.backend.vo.MovieVo;
import com.central.common.model.KpnSiteMovie;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 站点影片
 */
public interface IKpnSiteMovieService extends ISuperService<KpnSiteMovie> {
    /**
     * 搜索列表
     *
     * @param queryMovieCo 搜索条件
     * @return
     */
    PageResult<KpnSiteMovieVo> list(QueryMovieCo queryMovieCo);

    public void updateBatchStatusById(List<KpnSiteMovieStatusDto> list,SysUser user);

    public void updateBatchPayTypeById(List<KpnSiteMoviePayTypeDto> list, SysUser user);


    /**
     * 根据站点查询影片列表
     * @param params
     * @return
     */
    PageResult<MovieVo> findMovieList(Map<String, Object> params);

}

