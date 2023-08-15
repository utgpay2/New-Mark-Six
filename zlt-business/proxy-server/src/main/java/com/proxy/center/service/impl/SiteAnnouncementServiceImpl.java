package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;



import com.proxy.center.mapper.SiteAnnouncementMapper;
import com.proxy.center.service.ISiteAnnouncementService;


import com.central.common.model.SiteAnnouncement;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class SiteAnnouncementServiceImpl extends SuperServiceImpl<SiteAnnouncementMapper, SiteAnnouncement> implements ISiteAnnouncementService {


    @Override
    public List<SiteAnnouncement> findAnnouncementList(Long siteId) {

        LambdaQueryWrapper<SiteAnnouncement> wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(SiteAnnouncement::getSiteId,siteId).eq(SiteAnnouncement::getStatus,1);


        wrapper.orderByDesc(SiteAnnouncement::getCreateTime);
        List<SiteAnnouncement> list = baseMapper.selectList( wrapper);

        return list;
    }


}