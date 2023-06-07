package com.xxl.job.executor.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.DateUtil;
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
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, sysUser.getId());
        RedisRepository.delete(redisKey);
    }
    @Override
    public SysUser getSysUserById(Long memberId){
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, memberId);
        SysUser sysUser = (SysUser)RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(sysUser)) {
            sysUser = this.getById(memberId);
            RedisRepository.setExpire(redisKey, sysUser, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return sysUser;
    }

}

























