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
            String redisKey = StrUtil.format(RedisConstants.SITE_MYQUIZORDERS_LIST_KEY,quizOrders.getSiteId(),quizOrders.getMemberId(),"*");
            RedisRepository.delete(redisKey);
        }
    }
}