package com.central.porn.service;

import com.central.common.model.RptSiteMovieDate;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;

import java.util.List;


public interface IRptSiteMovieDateService extends ISuperService<RptSiteMovieDate> {
    /**
     * 站点影片日报表
     *
     * @param sid     站点id
     * @param movieId 影片id
     * @param date    日期
     */
    void saveRptSiteMovieDateVv(Long sid, Long movieId, String date);

    /**
     * 站点影片月排行
     *
     * @param sid       站点id
     * @return
     */
    List<KpnSiteMovieBaseVo> searchSiteMovieMonth(Long sid);
}
