package com.central.backend.service;

import com.central.backend.co.MoneyLogCo;
import com.central.backend.model.vo.MoneyLogVO;
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

     MoneyLogVO totalNumber(Map<String, Object> params, SysUser user);
}
