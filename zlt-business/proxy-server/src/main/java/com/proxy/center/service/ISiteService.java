package com.proxy.center.service;

import com.proxy.center.co.SiteCo;
import com.proxy.center.co.SiteUpdateCo;
import com.proxy.center.model.dto.ProxyRechargeDto;
import com.proxy.center.model.dto.SiteRechargeDto;
import com.proxy.center.vo.SiteListVo;
import com.proxy.center.vo.SiteVo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.Site;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/3
 */
public interface ISiteService extends ISuperService<Site> {

    PageResult<Site> findSiteList(SiteCo params);

    List<Site> findKpnSiteList(Map<String, Object> params);

    SiteVo findSiteTotal();


    Result saveOrUpdateSite(Site site);


    Result updateEnabledSite(SiteUpdateCo params);

    String getStringRandom(int length) ;


    List<SiteListVo> findSiteBoxList(Integer roleId);

    /**
     * 获取商户列表
     * @return
     */
    List<Site> getList();


    Result recharge(SiteRechargeDto siteRechargeDto, SysUser sysUser);

    PageResult getProxys(Map<String, Object> params, SysUser sysUser);

    Result rechargeProxy(ProxyRechargeDto proxyRechargeDto, SysUser sysUser);
}
