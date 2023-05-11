package com.central.backend.service.impl;

import com.central.backend.mapper.QuizOrderMapper;
import com.central.backend.service.IQuizOrderService;
import com.central.common.model.QuizOrder;
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
 * @date 2023-05-09 18:43:00
 */
@Slf4j
@Service
public class QuizOrderServiceImpl extends SuperServiceImpl<QuizOrderMapper, QuizOrder> implements IQuizOrderService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<QuizOrder> findList(Map<String, Object> params){
        Page<QuizOrder> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<QuizOrder> list  =  baseMapper.findList(page, params);
        return PageResult.<QuizOrder>builder().data(list).count(page.getTotal()).build();
    }
}
