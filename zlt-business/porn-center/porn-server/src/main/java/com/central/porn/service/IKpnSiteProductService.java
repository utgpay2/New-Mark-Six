package com.central.porn.service;

import com.central.common.model.SysUser;
import com.central.common.model.pay.KpnSiteProduct;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteProductVo;

import java.util.List;

public interface IKpnSiteProductService extends ISuperService<KpnSiteProduct> {
    /**
     * 获取站点产品列表
     *
     * @param sid 站点id
     * @return
     */
    List<KpnSiteProductVo> getSiteProducts(Long sid);

    /**
     * K币开通/续期vip
     * @param sysUser 会员
     * @param siteProduct 产品
     */
    void buyUseKb(SysUser sysUser, KpnSiteProduct siteProduct);
}

