package com.central.marksix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.RptSiteSearchDate;
import com.central.marksix.entity.vo.KpnSiteSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RptSiteSearchDateMapper extends BaseMapper<RptSiteSearchDate> {

    void saveRptSiteSearchDateNumber(@Param("sid") Long sid, @Param("date") String date, @Param("keywords") String keywords);

    List<KpnSiteSearchVo> getSiteSearchMonth(@Param("sid") Long sid, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
