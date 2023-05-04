package com.central.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnMoneyLogCo;
import com.central.backend.mapper.KpnMoneyLogMapper;
import com.central.backend.model.vo.KpnMoneyLogVO;
import com.central.backend.service.IKpnMoneyLogService;
import com.central.common.model.KpnMoneyLog;
import com.central.common.model.PageResult;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class KpnMoneyLogServiceImpl extends SuperServiceImpl<KpnMoneyLogMapper, KpnMoneyLog> implements IKpnMoneyLogService {


    @Override
    public PageResult<KpnMoneyLog> findMoneyLogList(KpnMoneyLogCo params) {
        Page<KpnMoneyLog> page = new Page<>(params.getPage(), params.getLimit());
        List<KpnMoneyLog> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnMoneyLog>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public KpnMoneyLogVO totalNumber(Map<String, Object> params, SysUser user){
        if(null!=user && user.getSiteId()!=null && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        KpnMoneyLogVO kpnMoneyLogVO =  baseMapper.totalNumber(params);

        return kpnMoneyLogVO;
    }
}