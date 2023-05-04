package com.central.backend.service.pay;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.pay.KpnSiteProduct;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * vip产品
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
public interface IKpnSiteProductService extends ISuperService<KpnSiteProduct> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<KpnSiteProduct> findListPage(Map<String, Object> params, SysUser user);
    List<KpnSiteProduct> findList(Map<String, Object> params, SysUser user);
    Result saveOrUpdateKpnSiteProduct(KpnSiteProduct kpnSiteProduct, SysUser user);
    Result deleteKpnSiteProduct(@PathVariable Long id);
}

