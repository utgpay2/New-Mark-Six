package com.central.user.service.impl;

import com.central.common.model.SiteLoginLog;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.user.mapper.SiteLoginLogMapper;
import com.central.user.service.ISiteLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@CacheConfig(cacheNames = {"loginLog"})
public class SiteLoginLogServiceImpl extends SuperServiceImpl<SiteLoginLogMapper, SiteLoginLog> implements ISiteLoginLogService {


}