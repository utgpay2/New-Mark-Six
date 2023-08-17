package com.proxy.center.service;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.WnData;
import com.central.common.service.ISuperService;
import com.proxy.center.vo.WnDataVo;

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
    PageResult<WnData> findList(Map<String, Object> params);
    Result saveOrUpdateWnData(WnData wnData, SysUser user);
    Result deleteWnData(Long id, Integer lotteryId);

    PageResult<WnDataVo> wnDatalist(Map<String, Object> params);
}

