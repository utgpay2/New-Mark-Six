package com.central.backend.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizDetails;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-30 13:00:21
 */
public interface IQuizDetailsService extends ISuperService<QuizDetails> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<QuizDetails> findList(Map<String, Object> params);
}

