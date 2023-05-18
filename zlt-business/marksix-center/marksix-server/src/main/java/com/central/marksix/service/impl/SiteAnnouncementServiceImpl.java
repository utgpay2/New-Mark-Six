package com.central.marksix.service.impl;

import com.central.common.model.SiteAnnouncement;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.AnnouncementUserVo;
import com.central.marksix.mapper.SiteAnnouncementMapper;
import com.central.marksix.service.ISiteAnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SiteAnnouncementServiceImpl extends SuperServiceImpl<SiteAnnouncementMapper, SiteAnnouncement> implements ISiteAnnouncementService {
    @Override
    public List<AnnouncementUserVo> findList(Map<String, Object> params){
        List<AnnouncementUserVo> list  =  baseMapper.findList(params);
        return list;
    }
}