package com.proxy.center.service.pay;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.pay.SiteBank;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * 收款银行渠道
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
public interface ISiteBankService extends ISuperService<SiteBank> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<SiteBank> findListPage(Map<String, Object> params, SysUser user);
    List<SiteBank> findList(Map<String, Object> params, SysUser user);
    Result saveOrUpdateKpnSiteBank(SiteBank siteBank, SysUser user);
    Result deleteKpnSiteBank(@PathVariable Long id);
}

