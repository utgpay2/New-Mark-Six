package com.central.porn.service;

import com.central.common.model.KpnSiteTopicMovie;
import com.central.common.service.ISuperService;

import java.util.List;


public interface IKpnSiteTopicMovieService extends ISuperService<KpnSiteTopicMovie> {

    /**
     * 获取专题最新10部影片
     *
     * @param sid      站点id
     * @param topicId  专题id
     * @param currPage 当前页
     * @param pageSize 每页条数
     * @return
     */
    List<Long> getTopicMovieIdsBySort(Long sid, Long topicId, Integer currPage, Integer pageSize);


    /**
     * 缓存站点专题影片
     *
     * @param sid     站点id
     * @param topicId 专题id
     * @param column  排序字段
     * @return
     */
    List<Long> getTopicMovieIdsSortedByColumn(Long sid, Long topicId, String column);

    /**
     * 站点id
     *
     * @param sid     站点id
     * @param topicId 专题id
     * @param movieId 影片id
     */
    void addTopicMovieVv(Long sid, Long topicId, Long movieId);
}
