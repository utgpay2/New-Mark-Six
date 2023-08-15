package com.proxy.center.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.RptSiteSummaryCo;
import com.proxy.center.mapper.RptSiteSummaryMapper;
import com.proxy.center.service.IRptSiteSummaryService;
import com.proxy.center.vo.RptSiteSummaryVo;
import com.central.common.model.PageResult;
import com.central.common.model.RptSiteSummary;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class RptSiteSummaryServiceImpl extends SuperServiceImpl<RptSiteSummaryMapper, RptSiteSummary> implements IRptSiteSummaryService {

    @Override
    public PageResult<RptSiteSummary> findSummaryList(RptSiteSummaryCo params) {
        Page<RptSiteSummary> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<RptSiteSummary> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.like(RptSiteSummary::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(RptSiteSummary::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(RptSiteSummary::getCreateTime, params.getEndTime());
        }
        wrapper.orderByDesc(RptSiteSummary::getDate);
        Page<RptSiteSummary> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<RptSiteSummary>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public RptSiteSummaryVo findSummaryTotal(RptSiteSummaryCo params) {
        return baseMapper.findSummaryTotal(params);
    }


    @Override
    public void addSummaryNewUserNum( Long siteId,  String siteCode,String siteName) {
        String date =DateUtil.formatDate(new Date());
        baseMapper.addSummaryNewUserNum(siteId,siteCode,siteName,date);
    }
}