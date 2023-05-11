package com.central.backend.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizChoose;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
public interface IQuizChooseService extends ISuperService<QuizChoose> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<QuizChoose> findList(Map<String, Object> params);
}

