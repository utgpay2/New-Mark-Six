package com.xxl.job.executor.service.impl;

import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import com.xxl.job.executor.mapper.SysUserMapper;
import com.xxl.job.executor.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * year
 */
@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {



    @Override
    public void addRewardMb(SysUser sysUser, BigDecimal rewardMb) {
        this.lambdaUpdate().eq(SysUser::getId, sysUser.getId())
                .setSql("`m_balance` = `m_balance` + " + rewardMb)
                .update();
    }

}

























