package com.central.backend.service;

import com.central.backend.vo.SiteMovieListVo;
import com.central.common.model.KpnSiteTopicMovie;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/20
 */
public interface IKpnSiteTopicMovieService extends ISuperService<KpnSiteTopicMovie> {

    Boolean saveOrUpdateTopicMovie(List<KpnSiteTopicMovie> list);

    List<KpnSiteTopicMovie> findTopicMovieTopicIdList(Long topicId);


    Boolean deleteTopicId(List<Long> movieIds) ;

    Boolean deleteId(Long topicMovieId);



    PageResult<SiteMovieListVo> findSiteMovieList(Map<String, Object> params);

}
