package com.central.marksix.service;

import com.central.common.model.SysUser;
import com.central.common.model.pay.SiteProduct;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.vo.SiteProductVo;

import java.util.List;

public interface ISiteProductService extends ISuperService<SiteProduct> {
    /**
     * 获取站点产品列表
     *
     * @param sid 站点id
     * @return
     */
    List<SiteProductVo> getSiteProducts(Long sid);

    /**
     * K币开通/续期vip
     * @param sysUser 会员
     * @param siteProduct 产品
     */
    void buyUseKb(SysUser sysUser, SiteProduct siteProduct);
}

