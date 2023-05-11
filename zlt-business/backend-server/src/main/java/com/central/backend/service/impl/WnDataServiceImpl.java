package com.central.backend.service.impl;

import com.central.backend.mapper.WnDataMapper;
import com.central.backend.service.IWnDataService;
import com.central.common.model.WnData;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
@Slf4j
@Service
public class WnDataServiceImpl extends SuperServiceImpl<WnDataMapper, WnData> implements IWnDataService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<WnData> findList(Map<String, Object> params){
        Page<WnData> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<WnData> list  =  baseMapper.findList(page, params);
        return PageResult.<WnData>builder().data(list).count(page.getTotal()).build();
    }
}
