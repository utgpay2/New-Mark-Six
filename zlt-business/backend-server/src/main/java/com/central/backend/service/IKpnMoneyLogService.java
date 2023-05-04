package com.central.backend.service;

import com.central.backend.co.KpnMoneyLogCo;
import com.central.backend.model.vo.KpnMoneyLogVO;
import com.central.common.model.KpnMoneyLog;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/8
 */
public interface IKpnMoneyLogService extends ISuperService<KpnMoneyLog> {

     PageResult<KpnMoneyLog> findMoneyLogList(KpnMoneyLogCo params);

     KpnMoneyLogVO totalNumber(Map<String, Object> params, SysUser user);
}
