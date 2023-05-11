package com.central.backend.service.impl;

import com.central.backend.mapper.QuizOrderSonMapper;
import com.central.backend.service.IQuizOrderSonService;
import com.central.common.model.QuizOrderSon;
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
public class QuizOrderSonServiceImpl extends SuperServiceImpl<QuizOrderSonMapper, QuizOrderSon> implements IQuizOrderSonService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<QuizOrderSon> findList(Map<String, Object> params){
        Page<QuizOrderSon> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<QuizOrderSon> list  =  baseMapper.findList(page, params);
        return PageResult.<QuizOrderSon>builder().data(list).count(page.getTotal()).build();
    }
}
