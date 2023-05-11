package com.central.marksix.service;

import com.central.common.model.PageResult;
import com.central.common.model.QuizChoose;
import com.central.common.service.ISuperService;

import java.util.List;
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
    List<QuizChoose> findList(Map<String, Object> params);
}

