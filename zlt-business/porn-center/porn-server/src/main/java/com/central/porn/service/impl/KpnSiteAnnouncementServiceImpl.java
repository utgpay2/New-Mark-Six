package com.central.porn.service.impl;

import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.vo.AnnouncementUserVo;
import com.central.porn.mapper.KpnSiteAnnouncementMapper;
import com.central.porn.service.IKpnSiteAnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KpnSiteAnnouncementServiceImpl extends SuperServiceImpl<KpnSiteAnnouncementMapper, KpnSiteAnnouncement> implements IKpnSiteAnnouncementService {
    @Override
    public List<AnnouncementUserVo> findList(Map<String, Object> params){
        List<AnnouncementUserVo> list  =  baseMapper.findList(params);
        return list;
    }
}