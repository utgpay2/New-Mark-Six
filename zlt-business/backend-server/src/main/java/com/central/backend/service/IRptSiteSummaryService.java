package com.central.backend.service;

import cn.hutool.core.date.DateUtil;
import com.central.backend.co.RptSiteSummaryCo;
import com.central.backend.vo.RptSiteSummaryVo;
import com.central.common.model.KpnSiteUserVipLog;
import com.central.common.model.PageResult;
import com.central.common.model.RptSiteSummary;
import com.central.common.service.ISuperService;

import java.math.BigDecimal;
import java.util.Date;


/*
 * @Author: Lulu
 * @Date: 2023/2/10
 */
public interface IRptSiteSummaryService extends ISuperService<RptSiteSummary> {

    PageResult<RptSiteSummary> findSummaryList( RptSiteSummaryCo params);


    RptSiteSummaryVo findSummaryTotal(RptSiteSummaryCo params);

    void addSummaryNum(KpnSiteUserVipLog KpnSiteUserVipLogInfo );

    void addSummaryNewUserNum(Long siteId,  String siteCode,String siteName);
}
