package com.central.marksix.service;

import com.central.common.model.Lottery;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.vo.SiteLotteryVO;

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
    List<SiteLotteryVO> findListBySiteId(Map<String, Object> params);
    List<SiteLotteryVO> findListByLotteryId(Map<String, Object> params);
}

