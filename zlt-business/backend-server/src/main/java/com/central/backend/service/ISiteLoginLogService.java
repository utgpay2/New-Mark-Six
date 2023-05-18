package com.central.backend.service;

import com.central.backend.co.SiteLoginLogCo;
import com.central.common.model.KpnSiteLoginLog;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;


/*
 * @Author: Lulu
 * @Date: 2023/2/7
 */
public interface ISiteLoginLogService extends ISuperService<KpnSiteLoginLog> {

    PageResult<KpnSiteLoginLog> findUserLoginLogList( SiteLoginLogCo params);

}
