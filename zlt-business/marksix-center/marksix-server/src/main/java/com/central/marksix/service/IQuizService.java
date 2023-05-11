package com.central.marksix.service;

import com.central.common.model.PageResult;
import com.central.common.model.Quiz;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
public interface IQuizService extends ISuperService<Quiz> {
    /**
     * 列表
     * @param params
     * @return
     */
    List<Quiz> findList(Map<String, Object> params);
}

