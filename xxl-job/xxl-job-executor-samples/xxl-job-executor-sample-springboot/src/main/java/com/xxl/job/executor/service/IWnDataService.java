package com.xxl.job.executor.service;

import com.central.common.model.PageResult;
import com.central.common.model.WnData;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
public interface IWnDataService extends ISuperService<WnData> {
    WnData lastOneWnData(Integer lotteryId);
    void updateWnData(WnData wnData);
    void saveWnData(WnData wnData);
}

