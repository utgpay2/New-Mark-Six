package com.central.porn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.KpnMovie;
import com.central.common.model.RptSiteMovieDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RptSiteMovieDateMapper extends BaseMapper<RptSiteMovieDate> {


    void saveRptSiteMovieDateVv(@Param("sid") Long sid, @Param("movieId") Long movieId, @Param("date") String date);

    List<RptSiteMovieDate> searchSiteMovieMonth(@Param("sid") Long sid, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
