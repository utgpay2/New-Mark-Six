package com.central.porn.service;


import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.AnnouncementUserVo;
import com.central.porn.entity.vo.AnnouncementVo;

import java.util.List;
import java.util.Map;

public interface IKpnSiteAnnouncementService extends ISuperService<KpnSiteAnnouncement> {
    List<AnnouncementUserVo> findList(Map<String, Object> params);
}
