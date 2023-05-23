package com.central.marksix.service;


import com.central.common.model.SiteAnnouncementUser;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.dto.SiteAnnouncementUserDto;

public interface ISiteAnnouncementUserService extends ISuperService<SiteAnnouncementUser> {
    public Result saveOrUpdateAnnUser(SiteAnnouncementUserDto dto, SysUser user);
}
