package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.model.Lottery;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.SiteLotteryVo;
import com.central.marksix.mapper.LotteryMapper;
import com.central.marksix.service.ILotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import com.central.common.constant.RedisConstants;

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
    public List<SiteLotteryVo> findListBySiteId(Map<String, Object> params){
        params.put("isDisplay", StatusEnum.ONE_TRUE.getStatus());
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_LIST_KEY, MapUtils.getInteger(params,"siteId"),
                true==ObjectUtil.isEmpty(params.get("sortBy"))? SortEnum.DESC.getCode():MapUtils.getInteger(params,"sortBy"), StatusEnum.ONE_TRUE.getStatus());
        List<SiteLotteryVo> list = (List<SiteLotteryVo>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public List<SiteLotteryVo> findListByLotteryId(Map<String, Object> params){
        params.put("isDisplay", StatusEnum.ONE_TRUE.getStatus());
        String redisKey = StrUtil.format(RedisConstants.LOTTERYID_LIST_KEY, MapUtils.getInteger(params,"lotteryId"), StatusEnum.ONE_TRUE.getStatus());
        List<SiteLotteryVo> list = (List<SiteLotteryVo>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
}
