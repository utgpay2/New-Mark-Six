package com.xxl.job.executor.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Lottery;
import com.central.common.model.enums.LotteryEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.vo.SiteLotteryVo;
import com.xxl.job.executor.mapper.LotteryMapper;
import com.xxl.job.executor.service.ILotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Slf4j
@Service
public class LotteryServiceImpl extends SuperServiceImpl<LotteryMapper, Lottery> implements ILotteryService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<SiteLotteryVo> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
    @Override
    public List<Lottery> findLotteryList(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_KEY);
        List<Lottery> list = (List<Lottery>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findLotteryList(params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public void updateLotteryStatus(Integer lotteryId, Integer stauts) {
        this.lambdaUpdate().eq(Lottery::getId, lotteryId)
                .setSql("`status` = "+ stauts)
                .update();
        Set<String> redisKeys = RedisRepository.keys(StrUtil.format(RedisConstants.SITE_LOTTERY_KEY));
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
    }
}
