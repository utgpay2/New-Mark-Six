package com.central.backend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.QuizDetailsMapper;
import com.central.backend.service.IQuizChooseService;
import com.central.backend.service.IQuizDetailsService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String redisKey = StrUtil.format(RedisConstants.SITE_QUIZDETAILS_LIST_KEY, MapUtils.getInteger(params,"quizId"),
                true== ObjectUtil.isEmpty(params.get("sortBy"))? SortEnum.ASC.getCode():MapUtils.getInteger(params,"sortBy"));
        List<QuizDetails> list = (List<QuizDetails>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList(params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public Result deleteQuizDetails(Long id){
        LambdaQueryWrapper<QuizChoose> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(QuizChoose::getQuizDetailsId,id);
        List<QuizChoose> list = quizChooseService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除规则明细，再删除规则主表");
        }else {
            this.removeById(id);
            String redisKey = StrUtil.format(RedisConstants.SITE_QUIZDETAILS_LIST_KEY, "*","*","*");
            RedisRepository.delete(redisKey);
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
        String redisKey = StrUtil.format(RedisConstants.SITE_QUIZDETAILS_LIST_KEY, "*","*","*");
        RedisRepository.delete(redisKey);
        return Result.succeed("保存成功");
    }
}
