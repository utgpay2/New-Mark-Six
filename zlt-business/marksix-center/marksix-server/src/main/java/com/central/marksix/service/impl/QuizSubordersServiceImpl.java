package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.QuizSuborders;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.marksix.mapper.QuizSubordersMapper;
import com.central.marksix.service.IQuizSubordersService;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 *
 * @author zlt
 * @date 2023-06-15 20:50:53
 */
@Slf4j
@Service
public class QuizSubordersServiceImpl extends SuperServiceImpl<QuizSubordersMapper, QuizSuborders> implements IQuizSubordersService {

    @Override
    public List<QuizSuborders> findList(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_ORDERS_SUB_LIST_KEY, MapUtils.getInteger(params,"orderNo"));
        List<QuizSuborders> list = (List<QuizSuborders>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
}
