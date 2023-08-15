package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.SiteLoginLogCo;
import com.proxy.center.mapper.SiteLoginLogMapper;
import com.proxy.center.service.ISiteLoginLogService;
import com.central.common.model.PageResult;
import com.central.common.model.SiteLoginLog;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SiteLoginLogServiceImpl extends SuperServiceImpl<SiteLoginLogMapper, SiteLoginLog> implements ISiteLoginLogService {


    @Override
    public PageResult<SiteLoginLog> findUserLoginLogList(SiteLoginLogCo params) {
        Page<SiteLoginLog> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<SiteLoginLog> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.eq(SiteLoginLog::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getUserName())){
            wrapper.eq(SiteLoginLog::getUserName, params.getUserName());
        }
        if (StringUtils.isNotBlank(params.getLoginIp())){
            wrapper.eq(SiteLoginLog::getLoginIp, params.getLoginIp());
        }

        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(SiteLoginLog::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(SiteLoginLog::getCreateTime, params.getEndTime());
        }

        wrapper.eq(SiteLoginLog::getType, UserTypeEnum.APP.name());
        wrapper.orderByDesc(SiteLoginLog::getCreateTime);
        Page<SiteLoginLog> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<SiteLoginLog>builder().data(list.getRecords()).count(total).build();
    }
}