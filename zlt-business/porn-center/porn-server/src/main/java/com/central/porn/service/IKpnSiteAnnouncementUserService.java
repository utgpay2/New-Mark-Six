package com.central.porn.service;


import com.central.common.model.KpnSiteAnnouncementUser;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.porn.entity.dto.KpnSiteAnnouncementUserDto;

import java.util.List;
import java.util.Map;

public interface IKpnSiteAnnouncementUserService extends ISuperService<KpnSiteAnnouncementUser> {
    public Result saveOrUpdateAnnUser(KpnSiteAnnouncementUserDto dto, SysUser user);
}
