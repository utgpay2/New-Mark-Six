package com.central.marksix.service;

import com.central.common.model.PageResult;
import com.central.common.model.WnData;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.vo.WnDataVo;

import java.util.Map;

/**
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
public interface IWnDataService extends ISuperService<WnData> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<WnDataVo> findList(Map<String, Object> params);
    WnDataVo lastOneWnData(Integer lotteryId);
}

