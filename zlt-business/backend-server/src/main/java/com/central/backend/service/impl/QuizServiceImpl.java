package com.central.backend.service.impl;

import com.central.backend.mapper.QuizMapper;
import com.central.backend.service.IQuizService;
import com.central.common.model.Quiz;
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
 * @date 2023-05-09 18:41:24
 */
@Slf4j
@Service
public class QuizServiceImpl extends SuperServiceImpl<QuizMapper, Quiz> implements IQuizService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<Quiz> findList(Map<String, Object> params){
        Page<Quiz> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<Quiz> list  =  baseMapper.findList(page, params);
        return PageResult.<Quiz>builder().data(list).count(page.getTotal()).build();
    }
}
