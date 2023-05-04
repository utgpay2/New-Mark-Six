package com.central.porn.service;

import com.central.common.model.KpnSitePromotion;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

public interface IKpnSitePromotionService extends ISuperService<KpnSitePromotion> {

    /**
     * 获取站点邀请好友配置
     *
     * @param siteId 站点id
     * @return 站点活动配置
     */
    KpnSitePromotion getBySiteId(Long siteId);

    /**
     * 记录推广数据
     *
     * @param sitePromotionConfig 站点配置
     * @param sysUser             当前会员
     * @param promoteUser         推广会员
     */
    void addPromotionDatas(KpnSitePromotion sitePromotionConfig, SysUser sysUser, SysUser promoteUser);
}
