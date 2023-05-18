package com.central.marksix.service.impl;

import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SiteUserMapper;
import com.central.marksix.service.ISiteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * user服务
 */
@Slf4j
@Service
public class SiteUserServiceImpl extends SuperServiceImpl<SiteUserMapper, SysUser> implements ISiteUserService {
}