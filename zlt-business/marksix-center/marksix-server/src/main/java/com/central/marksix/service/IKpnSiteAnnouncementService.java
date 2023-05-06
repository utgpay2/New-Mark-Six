package com.central.marksix.service;


import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.vo.AnnouncementUserVo;

import java.util.List;
import java.util.Map;

public interface IKpnSiteAnnouncementService extends ISuperService<KpnSiteAnnouncement> {
    List<AnnouncementUserVo> findList(Map<String, Object> params);
}
