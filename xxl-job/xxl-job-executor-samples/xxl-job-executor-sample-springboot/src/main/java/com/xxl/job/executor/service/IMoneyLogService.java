package com.xxl.job.executor.service;

import com.central.common.model.MoneyLog;
import com.central.common.service.ISuperService;

import java.util.List;

public interface IMoneyLogService extends ISuperService<MoneyLog> {
    void saveMoneyLogBatch(List<MoneyLog> moneyLogList);
}
