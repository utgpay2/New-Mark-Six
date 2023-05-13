package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.QuizMapper;
import com.central.backend.service.IQuizChooseService;
import com.central.backend.service.IQuizService;
import com.central.common.model.Quiz;
import com.central.common.model.QuizChoose;
import com.central.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
@Slf4j
@Service
public class QuizServiceImpl extends SuperServiceImpl<QuizMapper, Quiz> implements IQuizService {
    @Autowired
    private IQuizChooseService quizChooseService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<Quiz> findList(Map<String, Object> params){
        return baseMapper.findList(params);
    }
    @Override
    public Result deleteQuiz(Long id){
        LambdaQueryWrapper<QuizChoose> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(QuizChoose::getQuizId,id);
        List<QuizChoose> list = quizChooseService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除规则明细，再删除规则主表");
        }else {
            this.removeById(id);
            return Result.succeed("删除成功");
        }
    }
}
