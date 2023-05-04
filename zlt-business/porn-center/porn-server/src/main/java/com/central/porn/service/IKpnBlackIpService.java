package com.central.porn.service;

import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.KpnBlackIp;
import com.central.common.service.ISuperService;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
public interface IKpnBlackIpService extends ISuperService<KpnBlackIp> {

    /**
     * IP黑名单检查
     * @param ip
     * @return
     */
    public Boolean ipcheck(String ip, String siteId);
}

