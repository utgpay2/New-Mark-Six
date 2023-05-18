package com.central.backend.service;

import com.central.common.model.SiteServe;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 站点客服配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
public interface ISiteServeService extends ISuperService<SiteServe> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<SiteServe> findListPage(Map<String, Object> params, SysUser user);
    List<SiteServe> findList(Map<String, Object> params, SysUser user);
    Result saveOrUpdateKpnSiteServe(SiteServe siteServe, SysUser user);
}

