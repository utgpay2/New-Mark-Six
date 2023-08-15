package com.proxy.center.service;

import com.proxy.center.co.SiteLoginLogCo;
import com.central.common.model.PageResult;
import com.central.common.model.SiteLoginLog;
import com.central.common.service.ISuperService;


/*
 * @Author: Lulu
 * @Date: 2023/2/7
 */
public interface ISiteLoginLogService extends ISuperService<SiteLoginLog> {

    PageResult<SiteLoginLog> findUserLoginLogList(SiteLoginLogCo params);

}
