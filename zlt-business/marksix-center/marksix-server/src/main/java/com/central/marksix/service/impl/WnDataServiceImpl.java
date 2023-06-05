package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.RedisConstants;
import com.central.common.model.PageResult;
import com.central.common.model.WnData;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.WnDataMapper;
import com.central.marksix.service.IWnDataService;
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
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<WnData> findList(Map<String, Object> params){
        Page<WnData> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        String redisKey = StrUtil.format(RedisConstants.WNDATA_LIST_PAGE_KEY, MapUtils.getInteger(params,"lotteryId"),
                true==ObjectUtil.isEmpty(params.get("sortBy"))? SortEnum.ASC.getCode():MapUtils.getInteger(params,"sortBy"),
                MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<WnData> list  =  (List<WnData>)RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(list)) {
            list  =  baseMapper.findList(page, params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return PageResult.<WnData>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public WnData lastOneWnData(Integer lotteryId){
        String redisKey = StrUtil.format(RedisConstants.LASTONE_WNDATA_KEY, lotteryId);
        WnData wnData = (WnData)RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(wnData)) {
            wnData = baseMapper.lastOneWnData(lotteryId);
            RedisRepository.setExpire(redisKey, wnData, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return wnData;
    }
}
