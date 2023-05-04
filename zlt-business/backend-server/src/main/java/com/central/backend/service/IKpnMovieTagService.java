package com.central.backend.service;

import com.central.common.KpnMovieTag;
import com.central.common.model.KpnTag;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;


public interface IKpnMovieTagService extends ISuperService<KpnMovieTag> {
    /**
     * 根据标签ID查询所有电影
     *
     * @param params 标签ID，标签类型ID，电影ID
     * @return
     */
    List<KpnMovieTag> getKpnMovieTag(Map<String, Object> params);

    /**
     * 获取影片标签
     *
     * @param movieId 影片id
     * @return
     */
    List<KpnTag> getTagByMovieId(Long movieId);
}
