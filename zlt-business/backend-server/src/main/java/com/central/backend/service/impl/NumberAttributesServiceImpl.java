package com.central.backend.service.impl;

import com.central.backend.mapper.NumberAttributesMapper;
import com.central.backend.service.INumberAttributesService;
import com.central.common.model.NumberAttributes;
import com.central.common.model.Result;
import com.central.common.utils.DateUtil;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


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
     * @param params
     * @return
     */
    @Override
    public List<NumberAttributes> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
    @Override
    public Result addOneYearOfData(){



        return null;
    }
}
