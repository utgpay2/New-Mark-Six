package com.proxy.center.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.proxy.center.mapper.QuizMapper;
import com.proxy.center.model.dto.QuizDto;
import com.proxy.center.service.IQuizDetailsService;
import com.proxy.center.service.IQuizService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Quiz;
import com.central.common.model.QuizDetails;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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
    private IQuizDetailsService quizDetailsService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<Quiz> findList(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_LIST_KEY,
                MapUtils.getInteger(params,"siteId"),
                MapUtils.getInteger(params,"siteLotteryId"),
                MapUtils.getInteger(params,"siteCategoryId"));
        List<Quiz> list = (List<Quiz>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        Comparator<Quiz> comparator;
        if(ObjectUtil.isEmpty(params.get("sortBy"))||SortEnum.DESC.getCode() != MapUtils.getInteger(params,"sortBy")){
            comparator = Comparator.comparing(Quiz::getSort);//正序
        }else {
            comparator = Comparator.comparing(Quiz::getSort).reversed();//倒序
        }
        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    @Override
    public Result deleteQuiz(QuizDto quizDto){
        LambdaQueryWrapper<QuizDetails> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(QuizDetails::getQuizId,quizDto.getId());
        List<QuizDetails> list = quizDetailsService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除商户彩种分类(三类)，再删除彩种分类(二类)");
        }else {
            this.removeById(quizDto.getId());
            String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_LIST_KEY,
                    quizDto.getSiteId(),
                    quizDto.getSiteLotteryId(),
                    quizDto.getSiteCategoryId());
            Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
            for(String redisKey:redisKeys) {
                RedisRepository.delete(redisKey);
            }
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
        String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_LIST_KEY,
                quiz.getSiteId(),
                quiz.getSiteLotteryId(),
                quiz.getSiteCategoryId());
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }
}
