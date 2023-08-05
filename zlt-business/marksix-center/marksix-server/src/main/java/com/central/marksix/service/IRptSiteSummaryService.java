package com.central.marksix.service;

import com.central.common.model.RptSiteSummary;
import com.central.common.service.ISuperService;

public interface IRptSiteSummaryService extends ISuperService<RptSiteSummary> {

    /**
     * 商户当日新增会员数
     *
     * @param sid      商户id
     * @param siteCode 商户编码
     * @param siteName 商户名称
     */
    void addNewUserNum(Long sid, String siteCode, String siteName);


}
