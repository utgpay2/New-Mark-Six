package com.central.backend.service.impl;

import com.central.backend.mapper.QuizOrdersMapper;
import com.central.backend.service.IQuizOrdersService;
import com.central.common.model.QuizOrders;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
@Slf4j
@Service
public class QuizOrdersServiceImpl extends SuperServiceImpl<QuizOrdersMapper, QuizOrders> implements IQuizOrdersService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<QuizOrders> findList(Map<String, Object> params){
        Page<QuizOrders> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<QuizOrders> list  =  baseMapper.findList(page, params);
        return PageResult.<QuizOrders>builder().data(list).count(page.getTotal()).build();
    }
}
