package com.proxy.center.service;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SiteApp;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 商户app更新配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
public interface ISiteAppService extends ISuperService<SiteApp> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<SiteApp> findList(Map<String, Object> params, SysUser user);
    Result saveOrUpdateKpnSiteApp(SiteApp siteApp, SysUser user);
}

