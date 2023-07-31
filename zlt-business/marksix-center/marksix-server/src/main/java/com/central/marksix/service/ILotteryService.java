package com.central.marksix.service;

import com.central.common.model.Lottery;
import com.central.common.service.ISuperService;
import com.central.common.vo.SiteLotteryVo;

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
    List<SiteLotteryVo> findListBySiteId(Map<String, Object> params);
    List<Lottery> findListByLotteryId(Long lotteryId);
}

