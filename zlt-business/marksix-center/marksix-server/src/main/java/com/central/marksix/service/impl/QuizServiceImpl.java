package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.RedisConstants;
import com.central.common.model.PageResult;
import com.central.common.model.Quiz;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.SiteLotteryVo;
import com.central.marksix.mapper.QuizMapper;
import com.central.marksix.service.IQuizService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 竞猜分类（二类）
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
@Slf4j
@Service
public class QuizServiceImpl extends SuperServiceImpl<QuizMapper, Quiz> implements IQuizService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<Quiz> findList(Map<String, Object> params){
        if(null == params){
            params = new HashMap<>();
        }
        params.put("status", StatusEnum.ONE_TRUE.getStatus());
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_LIST_KEY,
                MapUtils.getInteger(params,"siteId"),
                MapUtils.getInteger(params,"siteLotteryId"),
                MapUtils.getInteger(params,"siteCategoryId"));
        List<Quiz> list = (List<Quiz>)RedisRepository.get(redisKey);
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
        return list.stream().filter(quiz -> StatusEnum.ONE_TRUE.getStatus().equals(quiz.getStatus()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
