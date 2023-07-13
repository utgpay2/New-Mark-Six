package com.central.backend.service;

import com.central.backend.model.dto.QuizChooseDto;
import com.central.common.model.PageResult;
import com.central.common.model.QuizChoose;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.common.vo.QuizChooseVo;

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
    List<QuizChooseVo> findList(Map<String, Object> params);
    Result deleteQuizChoose(QuizChooseDto quizChooseDto);
    Result saveOrUpdateQuizChoose(QuizChoose quizChoose, SysUser user);
}

