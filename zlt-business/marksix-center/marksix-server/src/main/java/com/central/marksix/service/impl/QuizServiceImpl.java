package com.central.marksix.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.PageResult;
import com.central.common.model.Quiz;
import com.central.common.model.enums.StatusEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.QuizMapper;
import com.central.marksix.service.IQuizService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public List<Quiz> findList(Map<String, Object> params){
        if(null == params){
            params = new HashMap<>();
        }
        params.put("status", StatusEnum.ONE_TRUE.getStatus());
        return baseMapper.findList( params);
    }
}
