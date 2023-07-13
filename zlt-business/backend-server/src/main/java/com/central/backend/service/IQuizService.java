package com.central.backend.service;

import com.central.backend.model.dto.QuizDto;
import com.central.common.model.PageResult;
import com.central.common.model.Quiz;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
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
    Result deleteQuiz(QuizDto quizDto);

    Result saveOrUpdateQuiz(Quiz quiz, SysUser user);
}

