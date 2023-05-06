package com.central.marksix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.KpnSiteTopicMovie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KpnSiteTopicMovieMapper extends BaseMapper<KpnSiteTopicMovie> {

    List<Long> getTopicMovieIdsSortedByColumn(@Param("sid") Long sid,@Param("topicId") Long topicId,@Param("column") String column);
}
