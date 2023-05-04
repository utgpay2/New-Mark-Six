package com.central.backend.mapper;

import com.central.backend.co.RptSiteSummaryCo;
import com.central.backend.vo.RptSiteSummaryVo;
import com.central.common.model.RptSiteSummary;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/*
 * @Author: Lulu
 * @Date: 2023/2/10
 */
@Mapper
public interface RptSiteSummaryMapper extends SuperMapper<RptSiteSummary> {



    RptSiteSummaryVo findSummaryTotal( @Param("r") RptSiteSummaryCo params);


    void addSummaryNum(@Param("siteId") Long siteId, @Param("siteCode") String siteCode,
                       @Param("siteName") String siteName, @Param("date") String date, @Param("recharge") BigDecimal recharge);


    void addSummaryNewUserNum(@Param("siteId") Long siteId, @Param("siteCode") String siteCode,
                       @Param("siteName") String siteName, @Param("date") String date);


}
