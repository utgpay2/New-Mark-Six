package com.xxl.job.executor.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.*;
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


}