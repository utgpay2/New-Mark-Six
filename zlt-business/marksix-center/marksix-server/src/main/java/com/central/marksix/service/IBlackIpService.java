package com.central.marksix.service;

import com.central.common.model.ipmanage.BlackIp;
import com.central.common.service.ISuperService;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
public interface IBlackIpService extends ISuperService<BlackIp> {

    /**
     * IP黑名单检查
     * @param ip
     * @return
     */
    public Boolean ipcheck(String ip, String siteId);
}

