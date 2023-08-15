package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.mapper.LotteryKjTimeMapper;
import com.proxy.center.service.ILotteryKjTimeService;
import com.central.common.model.LotteryKjTime;
import com.central.common.model.PageResult;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 开奖时间
 *
 * @author zlt
 * @date 2023-05-09 19:59:03
 */
@Slf4j
@Service
public class LotteryKjTimeServiceImpl extends SuperServiceImpl<LotteryKjTimeMapper, LotteryKjTime> implements ILotteryKjTimeService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<LotteryKjTime> findList(Map<String, Object> params){
        Page<LotteryKjTime> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<LotteryKjTime> list  =  baseMapper.findList(page, params);
        return PageResult.<LotteryKjTime>builder().data(list).count(page.getTotal()).build();
    }
}
