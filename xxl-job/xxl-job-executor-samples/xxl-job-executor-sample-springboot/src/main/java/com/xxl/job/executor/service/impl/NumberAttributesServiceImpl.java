package com.xxl.job.executor.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.constant.RedisConstants;
import com.central.common.model.NumberAttributes;
import com.central.common.model.Result;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.DateUtil;
import com.xxl.job.executor.mapper.NumberAttributesMapper;
import com.xxl.job.executor.service.INumberAttributesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 号码属性表
 *
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
@Slf4j
@Service
public class NumberAttributesServiceImpl extends SuperServiceImpl<NumberAttributesMapper, NumberAttributes> implements INumberAttributesService {
    /**
     * 列表
     * @return
     */
    @Override
    public List<NumberAttributes> findList(){

        String redisKey = StrUtil.format(RedisConstants.NUMBERATTRIBUTES_LIST_KEY, DateUtil.getYear(),"number");
        List<NumberAttributes> list = (List<NumberAttributes>) RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(list)) {
            LambdaQueryWrapper<NumberAttributes> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(NumberAttributes::getYear, DateUtil.getYear());
            list = baseMapper.selectList(wrapper);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
}
