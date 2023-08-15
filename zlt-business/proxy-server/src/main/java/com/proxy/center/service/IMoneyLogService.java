package com.proxy.center.service;

import com.proxy.center.co.MoneyLogCo;
import com.proxy.center.model.vo.MoneyLogVo;
import com.central.common.model.MoneyLog;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/8
 */
public interface IMoneyLogService extends ISuperService<MoneyLog> {

     PageResult<MoneyLog> findMoneyLogList(MoneyLogCo params);

     MoneyLogVo totalNumber(Map<String, Object> params, SysUser user);
}
