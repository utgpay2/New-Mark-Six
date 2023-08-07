package com.central.backend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.QuizDetailsMapper;
import com.central.backend.model.dto.QuizDetailsDto;
import com.central.backend.service.IQuizChooseService;
import com.central.backend.service.IQuizDetailsService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.*;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-30 13:00:21
 */
@Slf4j
@Service
public class QuizDetailsServiceImpl extends SuperServiceImpl<QuizDetailsMapper, QuizDetails> implements IQuizDetailsService {
    @Autowired
    private IQuizChooseService quizChooseService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<QuizDetails> findList(Map<String, Object> params){
        if(null == params){
            params = new HashMap<>();
        }
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_QUIZDETAILS_LIST_KEY,
                MapUtils.getInteger(params,"siteId"),
                MapUtils.getInteger(params,"siteLotteryId"),
                MapUtils.getInteger(params,"siteCategoryId"),
                MapUtils.getInteger(params,"quizId"));
        List<QuizDetails> list = (List<QuizDetails>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList(params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        Comparator<QuizDetails> comparator;
        if(ObjectUtil.isEmpty(params.get("sortBy"))||SortEnum.DESC.getCode() != MapUtils.getInteger(params,"sortBy")){
            comparator = Comparator.comparing(QuizDetails::getSort);//正序
        }else {
            comparator = Comparator.comparing(QuizDetails::getSort).reversed();//倒序
        }
        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    @Override
    public Result deleteQuizDetails(QuizDetailsDto quizDetailsDto){
        LambdaQueryWrapper<QuizChoose> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(QuizChoose::getQuizDetailsId,quizDetailsDto.getId());
        List<QuizChoose> list = quizChooseService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除规则明细，再删除商户彩种分类(三类)");
        }else {
            this.removeById(quizDetailsDto.getId());
            String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_QUIZDETAILS_LIST_KEY,
                    quizDetailsDto.getSiteId(),
                    quizDetailsDto.getSiteLotteryId(),
                    quizDetailsDto.getSiteCategoryId(),
                    quizDetailsDto.getQuizId());
            Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
            for(String redisKey:redisKeys) {
                RedisRepository.delete(redisKey);
            }
            return Result.succeed("删除成功");
        }
    }
    @Override
    public Result saveOrUpdateQuizDetails(QuizDetails quizDetails, SysUser user) {
        if (null != quizDetails.getId() && 0 != quizDetails.getId()) {
            quizDetails.setUpdateTime(new Date());
            quizDetails.setUpdateBy(null != user ? user.getUsername() : quizDetails.getUpdateBy());
        } else {
            quizDetails.setCreateBy(null != user ? user.getUsername() : quizDetails.getCreateBy());
            quizDetails.setCreateTime(new Date());
            quizDetails.setUpdateTime(new Date());
            quizDetails.setUpdateBy(null != user ? user.getUsername() : quizDetails.getCreateBy());
        }
        this.saveOrUpdate(quizDetails);
        String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_QUIZDETAILS_LIST_KEY,
                quizDetails.getSiteId(),
                quizDetails.getSiteLotteryId(),
                quizDetails.getSiteCategoryId(),
                quizDetails.getQuizId());
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }
}
