package com.central.marksix.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.PageResult;
import com.central.common.model.WnData;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.WnDataMapper;
import com.central.marksix.service.IWnDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
    @Override
    public WnData lastOneWnData(Integer lotteryId){
        return baseMapper.lastOneWnData(lotteryId);
    }
}
