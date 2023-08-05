package com.central.backend.service;

import com.central.backend.co.SiteCo;
import com.central.backend.co.SiteUpdateCo;
import com.central.backend.model.dto.ProxyRechargeDto;
import com.central.backend.model.dto.SiteRechargeDto;
import com.central.backend.vo.SiteListVo;
import com.central.backend.vo.SiteVo;
import com.central.common.model.Site;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
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


    Result saveOrUpdateSite( Site site);


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
