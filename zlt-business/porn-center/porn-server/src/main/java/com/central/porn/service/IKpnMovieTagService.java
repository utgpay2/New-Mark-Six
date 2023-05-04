package com.central.porn.service;

import com.central.common.KpnMovieTag;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnTagVo;

import java.util.List;


public interface IKpnMovieTagService extends ISuperService<KpnMovieTag> {
    /**
     * 获取电影标签
     *
     * @param movieId 电影id
     * @return
     */
    List<KpnTagVo> getTagByMovieId(Long movieId);

    /**
     * 获取站点标签id
     *
     * @param sid 站点id
     * @return
     */
    List<Long> getTagIdsBySiteId(Long sid);

    /**
     * 标签按字段排序
     *
     * @param sid    站点id
     * @param tagId  标签id
     * @param column 排序字段
     * @return
     */
    List<Long> getTagMovieIdsSortedByColumn(Long sid, Long tagId, String column);
}
