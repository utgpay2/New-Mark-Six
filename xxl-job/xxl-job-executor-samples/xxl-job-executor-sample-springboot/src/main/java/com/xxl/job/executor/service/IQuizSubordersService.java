package com.xxl.job.executor.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizSuborders;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zlt
 * @date 2023-06-15 20:50:53
 */
public interface IQuizSubordersService extends ISuperService<QuizSuborders> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<QuizSuborders> findListByPage(Map<String, Object> params);
    public List<QuizSuborders> findList(Map<String, Object> params);
}

