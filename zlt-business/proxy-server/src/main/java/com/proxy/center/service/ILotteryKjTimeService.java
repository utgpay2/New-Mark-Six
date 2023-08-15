package com.proxy.center.service;

import com.central.common.model.LotteryKjTime;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 开奖时间
 *
 * @author zlt
 * @date 2023-05-09 19:59:03
 */
public interface ILotteryKjTimeService extends ISuperService<LotteryKjTime> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<LotteryKjTime> findList(Map<String, Object> params);
}

