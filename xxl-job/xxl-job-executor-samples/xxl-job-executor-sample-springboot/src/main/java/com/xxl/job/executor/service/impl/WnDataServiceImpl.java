package com.xxl.job.executor.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.RedisConstants;
import com.central.common.model.PageResult;
import com.central.common.model.WnData;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.xxl.job.executor.mapper.WnDataMapper;
import com.xxl.job.executor.service.IWnDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
@Slf4j
@Service
public class WnDataServiceImpl extends SuperServiceImpl<WnDataMapper, WnData> implements IWnDataService {
    @Override
    public WnData lastOneWnData(Integer lotteryId){
        String redisKey = StrUtil.format(RedisConstants.LASTONE_WNDATA_KEY, lotteryId);
        WnData wnData = (WnData) RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(wnData)) {
            wnData = baseMapper.lastOneWnData(lotteryId);
            RedisRepository.setExpire(redisKey, wnData, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return wnData;
    }
    @Override
    public void updateWnDataStatus(WnData wnData) {
        this.lambdaUpdate().eq(WnData::getId, wnData.getId())
                .setSql("`status` = "+ wnData.getStatus())
                .update();
        String redisKey = StrUtil.format(RedisConstants.LASTONE_WNDATA_KEY, wnData.getLotteryId());
        RedisRepository.delete(redisKey);
    }
}
