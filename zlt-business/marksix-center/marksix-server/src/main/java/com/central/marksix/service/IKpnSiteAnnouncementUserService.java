package com.central.marksix.service;


import com.central.common.model.KpnSiteAnnouncementUser;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.dto.KpnSiteAnnouncementUserDto;

public interface IKpnSiteAnnouncementUserService extends ISuperService<KpnSiteAnnouncementUser> {
    public Result saveOrUpdateAnnUser(KpnSiteAnnouncementUserDto dto, SysUser user);
}
