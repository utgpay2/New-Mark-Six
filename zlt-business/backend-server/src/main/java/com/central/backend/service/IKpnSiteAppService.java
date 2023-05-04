package com.central.backend.service;

import com.central.common.model.KpnSiteApp;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 站点app更新配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
public interface IKpnSiteAppService extends ISuperService<KpnSiteApp> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<KpnSiteApp> findList(Map<String, Object> params, SysUser user);
    Result saveOrUpdateKpnSiteApp(KpnSiteApp kpnSiteApp, SysUser user);
}

