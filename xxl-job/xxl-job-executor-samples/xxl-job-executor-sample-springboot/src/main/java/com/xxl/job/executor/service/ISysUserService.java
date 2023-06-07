package com.xxl.job.executor.service;

import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.math.BigDecimal;

/**
 * @author zlt
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public interface ISysUserService extends ISuperService<SysUser> {



    /**
     * 增加奖励M币数
     *
     * @param sysUser  登录会员
     * @param rewardMb 奖励M币数
     */
    void addRewardMb(SysUser sysUser, BigDecimal rewardMb);

    /**
     * 根据用户ID查询用户
     * @param memberId
     * @return
     */
    SysUser getSysUserById(Long memberId);
}
