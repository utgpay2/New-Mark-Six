package com.central.backend.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizOrderSon;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
public interface IQuizOrderSonService extends ISuperService<QuizOrderSon> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<QuizOrderSon> findList(Map<String, Object> params);
}

