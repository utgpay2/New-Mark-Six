package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnSiteLoginLogCo;
import com.central.backend.mapper.KpnSiteLoginLogMapper;
import com.central.backend.service.IKpnSiteLoginLogService;
import com.central.common.model.KpnSiteLoginLog;
import com.central.common.model.PageResult;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KpnSiteLoginLogServiceImpl extends SuperServiceImpl<KpnSiteLoginLogMapper, KpnSiteLoginLog> implements IKpnSiteLoginLogService {


    @Override
    public PageResult<KpnSiteLoginLog> findUserLoginLogList(KpnSiteLoginLogCo params) {
        Page<KpnSiteLoginLog> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<KpnSiteLoginLog> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.eq(KpnSiteLoginLog::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getUserName())){
            wrapper.eq(KpnSiteLoginLog::getUserName, params.getUserName());
        }
        if (StringUtils.isNotBlank(params.getLoginIp())){
            wrapper.eq(KpnSiteLoginLog::getLoginIp, params.getLoginIp());
        }

        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(KpnSiteLoginLog::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(KpnSiteLoginLog::getCreateTime, params.getEndTime());
        }

        wrapper.eq(KpnSiteLoginLog::getType, UserTypeEnum.APP.name());
        wrapper.orderByDesc(KpnSiteLoginLog::getCreateTime);
        Page<KpnSiteLoginLog> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<KpnSiteLoginLog>builder().data(list.getRecords()).count(total).build();
    }
}