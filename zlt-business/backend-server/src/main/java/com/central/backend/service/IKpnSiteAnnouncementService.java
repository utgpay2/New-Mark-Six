package com.central.backend.service;


import com.central.backend.co.KpnSiteAnnouncementCo;
import com.central.backend.co.KpnSiteAnnouncementUpdateCo;
import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;

public interface IKpnSiteAnnouncementService extends ISuperService<KpnSiteAnnouncement> {

    PageResult<KpnSiteAnnouncement> findAnnouncementList(KpnSiteAnnouncementCo params) ;


    Result saveOrUpdateAnnouncement(KpnSiteAnnouncement sysNotice) ;



    KpnSiteAnnouncement selectById(Long id) ;


    boolean delAnnouncementId(Long id);

    Result updateEnabled(KpnSiteAnnouncementUpdateCo params);


}
