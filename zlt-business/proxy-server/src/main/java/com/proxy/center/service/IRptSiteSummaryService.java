package com.proxy.center.service;

import com.proxy.center.co.RptSiteSummaryCo;
import com.proxy.center.vo.RptSiteSummaryVo;
import com.central.common.model.PageResult;
import com.central.common.model.RptSiteSummary;
import com.central.common.service.ISuperService;


/*
 * @Author: Lulu
 * @Date: 2023/2/10
 */
public interface IRptSiteSummaryService extends ISuperService<RptSiteSummary> {

    PageResult<RptSiteSummary> findSummaryList(RptSiteSummaryCo params);


    RptSiteSummaryVo findSummaryTotal(RptSiteSummaryCo params);

    void addSummaryNewUserNum(Long siteId, String siteCode, String siteName);
}
