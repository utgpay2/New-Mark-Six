package com.central.backend.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizOrder;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:00
 */
public interface IQuizOrderService extends ISuperService<QuizOrder> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<QuizOrder> findList(Map<String, Object> params);
}

