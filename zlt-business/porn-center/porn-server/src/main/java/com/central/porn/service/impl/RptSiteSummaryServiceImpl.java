package com.central.porn.service.impl;

import cn.hutool.core.date.DateUtil;
import com.central.common.model.RptSiteSummary;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.RptSiteSummaryMapper;
import com.central.porn.service.IRptSiteSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class RptSiteSummaryServiceImpl extends SuperServiceImpl<RptSiteSummaryMapper, RptSiteSummary> implements IRptSiteSummaryService {
    @Override
    public void addNewUserNum(Long sid, String siteCode, String siteName) {
        String today = DateUtil.formatDate(new Date());
        this.baseMapper.addNewUserNum(sid, siteCode, siteName, today);
    }

    @Override
    public void addNewVipNum(Long sid, String siteCode, String siteName) {
        String today = DateUtil.formatDate(new Date());
        this.baseMapper.addNewVipNum(sid, siteCode, siteName, today);
    }
}