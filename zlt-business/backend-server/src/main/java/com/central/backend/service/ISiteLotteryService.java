package com.central.backend.service;

import com.central.backend.model.vo.SiteLotteryVo;
import com.central.common.model.Result;
import com.central.common.model.SiteLottery;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 彩种下注分类
 *
 * @author zlt
 * @date 2023-05-11 18:50:09
 */
public interface ISiteLotteryService extends ISuperService<SiteLottery> {
    /**
     * 列表
     * @param params
     * @return
     */
    List<SiteLotteryVo> findList(Map<String, Object> params);
    Result deleteSiteLottery(Long id);

    Result saveOrUpdateSiteLottery(SiteLottery siteLottery, SysUser user);
}

