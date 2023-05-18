package com.central.backend.service;


import com.central.backend.co.SiteAnnouncementCo;
import com.central.backend.co.SiteAnnouncementUpdateCo;
import com.central.common.model.SiteAnnouncement;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;

public interface ISiteAnnouncementService extends ISuperService<SiteAnnouncement> {

    PageResult<SiteAnnouncement> findAnnouncementList(SiteAnnouncementCo params) ;


    Result saveOrUpdateAnnouncement(SiteAnnouncement sysNotice) ;



    SiteAnnouncement selectById(Long id) ;


    boolean delAnnouncementId(Long id);

    Result updateEnabled(SiteAnnouncementUpdateCo params);


}
