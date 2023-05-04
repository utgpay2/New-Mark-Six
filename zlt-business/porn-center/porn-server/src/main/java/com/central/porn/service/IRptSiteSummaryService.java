package com.central.porn.service;

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

    /**
     * 站点当日新增vip数
     *
     * @param siteId   站点id
     * @param siteCode 站点编码
     * @param siteName 站点名称
     */
    void addNewVipNum(Long siteId, String siteCode, String siteName);
}
