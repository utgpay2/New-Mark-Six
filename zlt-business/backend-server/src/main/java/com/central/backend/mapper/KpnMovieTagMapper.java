package com.central.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.KpnMovieTag;
import com.central.common.model.KpnTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface KpnMovieTagMapper extends BaseMapper<KpnMovieTag> {
    List<KpnMovieTag> findList( @Param("p") Map<String, Object> params);

    /**
     * 获取影片标签
     * @param movieId 影片id
     * @return
     */
    List<KpnTag> getTagByMovieId(@Param("movieId") Long movieId);
}
