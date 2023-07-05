package com.central.backend.service;

import com.central.backend.model.dto.CopyBettingSiteDto;
import com.central.common.model.Result;
import com.central.common.model.SysUser;


/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
public interface ICopyBettingRulesService{
    Result copybettingsite(CopyBettingSiteDto copyBettingSiteDto, SysUser user);
    Result copybettinglottery(Long sourceSiteLotteryId,Long targetSiteLotteryId, SysUser user);
}

