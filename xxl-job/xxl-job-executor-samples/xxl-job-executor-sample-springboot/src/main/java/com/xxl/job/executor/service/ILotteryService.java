package com.xxl.job.executor.service;

import com.central.common.model.Lottery;
import com.central.common.service.ISuperService;
import com.xxl.job.executor.entity.vo.SiteLotteryVO;

import java.util.List;
import java.util.Map;

/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
public interface ILotteryService extends ISuperService<Lottery> {
    /**
     * 列表
     * @param params
     * @return
     */
    List<SiteLotteryVO> findList(Map<String, Object> params);
    public void updateLotteryStatus(Integer lotteryId, Integer stauts);
}

