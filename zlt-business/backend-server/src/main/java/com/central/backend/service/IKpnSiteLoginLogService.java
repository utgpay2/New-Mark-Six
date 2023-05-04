package com.central.backend.service;

import com.central.backend.co.KpnSiteLoginLogCo;
import com.central.common.model.KpnSiteLoginLog;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;


/*
 * @Author: Lulu
 * @Date: 2023/2/7
 */
public interface IKpnSiteLoginLogService extends ISuperService<KpnSiteLoginLog> {

    PageResult<KpnSiteLoginLog> findUserLoginLogList( KpnSiteLoginLogCo params);

}
