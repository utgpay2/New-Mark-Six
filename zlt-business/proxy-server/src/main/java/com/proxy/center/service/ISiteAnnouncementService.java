package com.proxy.center.service;



import com.central.common.model.SiteAnnouncement;
import com.central.common.service.ISuperService;

import java.util.List;

public interface ISiteAnnouncementService extends ISuperService<SiteAnnouncement> {

    List<SiteAnnouncement> findAnnouncementList(Long siteId) ;




}
