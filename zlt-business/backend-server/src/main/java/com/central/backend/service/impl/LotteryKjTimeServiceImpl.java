package com.central.backend.service.impl;

import com.central.backend.mapper.LotteryKjTimeMapper;
import com.central.backend.service.ILotteryKjTimeService;
import com.central.common.model.LotteryKjTime;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


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
