package com.xxl.job.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.model.NumberAttributes;
import com.central.common.model.Result;
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
        LambdaQueryWrapper<NumberAttributes> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(NumberAttributes::getYear, DateUtil.getYear());
        return baseMapper.selectList(wrapper);
    }
}
