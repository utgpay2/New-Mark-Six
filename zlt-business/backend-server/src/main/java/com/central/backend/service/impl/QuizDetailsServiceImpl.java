package com.central.backend.service.impl;

import com.central.backend.mapper.QuizDetailsMapper;
import com.central.backend.service.IQuizDetailsService;
import com.central.common.model.QuizDetails;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-30 13:00:21
 */
@Slf4j
@Service
public class QuizDetailsServiceImpl extends SuperServiceImpl<QuizDetailsMapper, QuizDetails> implements IQuizDetailsService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<QuizDetails> findList(Map<String, Object> params){
        Page<QuizDetails> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<QuizDetails> list  =  baseMapper.findList(page, params);
        return PageResult.<QuizDetails>builder().data(list).count(page.getTotal()).build();
    }
}
