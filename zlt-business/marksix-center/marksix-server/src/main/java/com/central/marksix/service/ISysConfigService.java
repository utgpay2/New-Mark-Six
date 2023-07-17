package com.central.marksix.service;

import com.central.common.model.SysConfig;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

public interface ISysConfigService extends ISuperService<SysConfig> {
    String getUrl(Integer platformType);
}
