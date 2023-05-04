package com.central.porn.service.impl;

import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnSiteUserMapper;
import com.central.porn.service.IKpnSiteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * user服务
 */
@Slf4j
@Service
public class KpnSiteUserServiceImpl extends SuperServiceImpl<KpnSiteUserMapper, SysUser> implements IKpnSiteUserService {
}