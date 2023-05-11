package com.central.backend.service.impl;

import com.central.backend.mapper.LotteryMapper;
import com.central.backend.service.ILotteryService;
import com.central.common.model.Lottery;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Slf4j
@Service
public class LotteryServiceImpl extends SuperServiceImpl<LotteryMapper, Lottery> implements ILotteryService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<Lottery> findList(Map<String, Object> params){
        Page<Lottery> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<Lottery> list  =  baseMapper.findList(page, params);
        return PageResult.<Lottery>builder().data(list).count(page.getTotal()).build();
    }
}
