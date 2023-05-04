package com.central.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.KpnMovie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface KpnMovieMapper extends BaseMapper<KpnMovie> {
    List<KpnMovie> findList( @Param("p") Map<String, Object> params);

}
