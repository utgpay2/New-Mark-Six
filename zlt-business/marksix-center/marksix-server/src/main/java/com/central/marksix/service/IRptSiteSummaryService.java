package com.central.marksix.service;

import com.central.common.model.RptSiteSummary;
import com.central.common.service.ISuperService;

public interface IRptSiteSummaryService extends ISuperService<RptSiteSummary> {

    /**
     * 站点当日新增会员数
     *
     * @param sid      站点id
     * @param siteCode 站点编码
     * @param siteName 站点名称
     */
    void addNewUserNum(Long sid, String siteCode, String siteName);


}
