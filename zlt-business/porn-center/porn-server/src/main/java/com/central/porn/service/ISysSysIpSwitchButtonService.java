package com.central.porn.service;

import com.central.common.model.ipmanage.SysIpSwitchButton;
import com.central.common.service.ISuperService;

import java.util.List;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:07:56
 */
public interface ISysSysIpSwitchButtonService extends ISuperService<SysIpSwitchButton> {
    /**
     * 列表
     * @return
     */
    List<SysIpSwitchButton> findList();
}

