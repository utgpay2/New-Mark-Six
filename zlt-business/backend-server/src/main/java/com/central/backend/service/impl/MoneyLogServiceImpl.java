package com.central.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.MoneyLogCo;
import com.central.backend.mapper.MoneyLogMapper;
import com.central.backend.model.vo.MoneyLogVo;
import com.central.backend.service.IMoneyLogService;
import com.central.common.model.MoneyLog;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class MoneyLogServiceImpl extends SuperServiceImpl<MoneyLogMapper, MoneyLog> implements IMoneyLogService {


    @Override
    public PageResult<MoneyLog> findMoneyLogList(MoneyLogCo params) {
        Page<MoneyLog> page = new Page<>(params.getPage(), params.getLimit());
        List<MoneyLog> list  =  baseMapper.findList(page, params);
        return PageResult.<MoneyLog>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public MoneyLogVo totalNumber(Map<String, Object> params, SysUser user){
        if(null!=user && user.getSiteId()!=null && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        MoneyLogVo moneyLogVO =  baseMapper.totalNumber(params);

        return moneyLogVO;
    }
}