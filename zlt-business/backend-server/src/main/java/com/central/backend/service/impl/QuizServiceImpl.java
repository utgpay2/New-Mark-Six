package com.central.backend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.QuizMapper;
import com.central.backend.service.IQuizChooseService;
import com.central.backend.service.IQuizService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.*;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.Date;
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
        String redisKey = StrUtil.format(RedisConstants.SITE_QUIZ_LIST_KEY, MapUtils.getInteger(params,"siteCategoryId"),
                true== ObjectUtil.isEmpty(params.get("sortBy"))? SortEnum.ASC.getCode():MapUtils.getInteger(params,"sortBy"));
        List<Quiz> list = (List<Quiz>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public Result deleteQuiz(Long id){
        LambdaQueryWrapper<QuizChoose> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(QuizChoose::getQuizDetailsId,id);
        List<QuizChoose> list = quizChooseService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除规则明细，再删除规则主表");
        }else {
            this.removeById(id);
            String redisKey = StrUtil.format(RedisConstants.SITE_QUIZ_LIST_KEY, "*","*");
            RedisRepository.delete(redisKey);
            String redisKey1 = StrUtil.format(RedisConstants.SITE_QUIZ_LIST_KEY, "*","*","*");
            RedisRepository.delete(redisKey1);
            return Result.succeed("删除成功");
        }
    }
    @Override
    public Result saveOrUpdateQuiz(Quiz quiz, SysUser user) {
        if (null != quiz.getId() && 0 != quiz.getId()) {
            quiz.setUpdateTime(new Date());
            quiz.setUpdateBy(null != user ? user.getUsername() : quiz.getUpdateBy());
        } else {
            quiz.setCreateBy(null != user ? user.getUsername() : quiz.getCreateBy());
            quiz.setCreateTime(new Date());
            quiz.setUpdateTime(new Date());
            quiz.setUpdateBy(null != user ? user.getUsername() : quiz.getCreateBy());
        }
        this.saveOrUpdate(quiz);
        String redisKey = StrUtil.format(RedisConstants.SITE_QUIZ_LIST_KEY, "*","*");
        RedisRepository.delete(redisKey);
        String redisKey1 = StrUtil.format(RedisConstants.SITE_QUIZ_LIST_KEY, "*","*","*");
        RedisRepository.delete(redisKey1);
        return Result.succeed("保存成功");
    }
}
