package com.central.user.jobs;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.central.common.constant.PornConstants;
import com.central.common.constant.SecurityConstants;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.user.service.ISysUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class CheckUserOfflineJob implements SimpleJob {

    @Autowired
    private ISysUserService userService;

    @SneakyThrows
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("CheckUserOfflineJob->params:{}, time:{}", shardingContext.getJobParameter(), LocalDateTime.now());

        Date oneHourBeforeTime = DateUtil.offsetHour(new Date(), -1);

        List<SysUser> onlineUserList = userService.lambdaQuery()
                .le(SysUser::getLoginTime, oneHourBeforeTime)
//                .eq(SysUser::getOnlineStatus, 1)
                .eq(SysUser::getType, "APP")
                .last(" limit 1000")
                .list();
        log.info("当前在线人数:{}", onlineUserList);

        String redisKey = SecurityConstants.REDIS_UNAME_TO_ACCESS + "online";
        for (SysUser sysUser : onlineUserList) {
            Set<String> onlineUserKey = RedisRepository.keys(redisKey + PornConstants.Symbol.COLON + sysUser.getUsername());
            if (CollUtil.isEmpty(onlineUserKey)) {
//                sysUser.setOnlineStatus(2);
                userService.saveOrUpdateUser(sysUser);
            }
        }

    }
}
