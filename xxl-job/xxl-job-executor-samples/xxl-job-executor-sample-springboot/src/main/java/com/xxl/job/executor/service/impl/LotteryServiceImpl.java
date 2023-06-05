package com.xxl.job.executor.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Lottery;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.xxl.job.executor.entity.vo.SiteLotteryVO;
import com.xxl.job.executor.mapper.LotteryMapper;
import com.xxl.job.executor.service.ILotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
    public List<SiteLotteryVO> findList(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.LOTTERYID_LIST_KEY, MapUtils.getInteger(params,"lotteryId"), StatusEnum.ONE_TRUE.getStatus());
        List<SiteLotteryVO> list = (List<SiteLotteryVO>)RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public void updateLotteryStatus(Integer lotteryId, Integer stauts) {
        this.lambdaUpdate().eq(Lottery::getId, lotteryId)
                .setSql("`status` = "+ stauts)
                .update();
        String redisKey = StrUtil.format(RedisConstants.LOTTERYID_LIST_KEY, lotteryId, StatusEnum.ONE_TRUE.getStatus());
        RedisRepository.delete(redisKey);
    }
}
