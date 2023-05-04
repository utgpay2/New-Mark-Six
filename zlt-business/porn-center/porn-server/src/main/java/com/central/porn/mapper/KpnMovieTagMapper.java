package com.central.porn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.KpnMovieTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KpnMovieTagMapper extends BaseMapper<KpnMovieTag> {

    /**
     * 获取站点所有标签id
     *
     * @param sid 站点id
     * @return
     */
    List<Long> getTagIdsBySiteId(@Param("sid") Long sid);

    /**
     * 查询标签影片id集合
     *
     * @param sid    站点id
     * @param tagId  标签id
     * @param column 排序字段
     * @return
     */
    List<Long> getTageMovieIdsSortedByColumn(@Param("sid") Long sid,@Param("tagId") Long tagId,@Param("column") String column);
}
