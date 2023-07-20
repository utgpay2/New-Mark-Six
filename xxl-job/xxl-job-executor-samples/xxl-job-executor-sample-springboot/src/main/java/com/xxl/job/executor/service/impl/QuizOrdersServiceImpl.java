package com.xxl.job.executor.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.RedisConstants;
import com.central.common.model.*;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.xxl.job.executor.mapper.QuizOrdersMapper;
import com.xxl.job.executor.service.IQuizOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class QuizOrdersServiceImpl extends SuperServiceImpl<QuizOrdersMapper, QuizOrders> implements IQuizOrdersService {
    @Override
    public PageResult<QuizOrders> findList(Map<String, Object> params) {
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<QuizOrders> list  =  baseMapper.findList(page, params);
        return PageResult.<QuizOrders>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public void saveOrUpdateQuizOrdersBatch(List<QuizOrders> ordersList){
        this.saveBatch(ordersList);
        for(QuizOrders quizOrders:ordersList) {
            //删除订单缓存
            //删除投注订单缓存
            String ordersRedisKeys = StrUtil.format(RedisConstants.SITE_LOTTERY_ORDERS_MY_LIST_KEY, quizOrders.getSiteId(), quizOrders.getId(),"*","*","*","*","*");
            Set<String> redisKeys = RedisRepository.keys(ordersRedisKeys);
            for(String redisKey:redisKeys) {
                RedisRepository.delete(redisKey);
            }
            //删除统计投注订单缓存
            String statiOrdersRedisKeys = StrUtil.format(RedisConstants.SITE_LOTTERY_ORDERS_MYSTATI_LIST_KEY, quizOrders.getSiteId(), quizOrders.getId(),"*","*");
            Set<String> statiOrdersRedisKey = RedisRepository.keys(statiOrdersRedisKeys);
            for(String redisKey:statiOrdersRedisKey) {
                RedisRepository.delete(redisKey);
            }
        }
    }
    @Override
    public BigDecimal sumTotalPrice(Map<String, Object> params) {
        return baseMapper.sumTotalPrice(params);
    }

}