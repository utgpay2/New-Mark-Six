package com.central.backend.service;


import com.central.common.model.KillOdds;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 设置杀率
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
public interface IKillOddsService extends ISuperService<KillOdds> {

    List<KillOdds> findList();
    Result saveOrUpdateKillOdds(KillOdds killOdds, SysUser user);


}

