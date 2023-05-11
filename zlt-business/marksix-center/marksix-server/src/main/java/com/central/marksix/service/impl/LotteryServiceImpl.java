package com.central.marksix.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.Lottery;
import com.central.common.model.PageResult;
import com.central.common.model.enums.StatusEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.LotteryMapper;
import com.central.marksix.service.ILotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public List<Lottery> findList(Map<String, Object> params){
        if(null==params){
            params = new HashMap<>();
        }
        params.put("isDisplay", StatusEnum.ONE_FALSE.getStatus());
        return baseMapper.findList( params);
    }
}
