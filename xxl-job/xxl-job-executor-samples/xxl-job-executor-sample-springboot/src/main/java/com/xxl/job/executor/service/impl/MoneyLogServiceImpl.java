package com.xxl.job.executor.service.impl;

import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.MoneyLog;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.xxl.job.executor.mapper.MoneyLogMapper;
import com.xxl.job.executor.service.IMoneyLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class MoneyLogServiceImpl extends SuperServiceImpl<MoneyLogMapper, MoneyLog> implements IMoneyLogService {

    @Override
    public void saveMoneyLogBatch(List<MoneyLog> moneyLogList){
        this.saveBatch(moneyLogList);
        for(MoneyLog moneyLog:moneyLogList) {
            String redisKey = StrUtil.format(RedisConstants.SITE_MONEYLOG_KEY, moneyLog.getUserId());
            RedisRepository.delete(redisKey);
        }
    }
}



























