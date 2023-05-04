package com.central.porn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.RptSiteSearchDate;
import com.central.common.model.RptSiteSearchTotal;
import com.central.porn.entity.vo.KpnSiteSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RptSiteSearchTotalMapper extends BaseMapper<RptSiteSearchTotal> {

    void saveRptSiteSearchTotalNumber(@Param("sid") Long sid, @Param("keywords") String keywords);

    List<KpnSiteSearchVo> getSiteSearchTotal(@Param("sid") Long sid);
}
