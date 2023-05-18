package com.central.marksix.service;

import com.central.common.model.SitePromotion;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

public interface ISitePromotionService extends ISuperService<SitePromotion> {

    /**
     * 获取站点邀请好友配置
     *
     * @param siteId 站点id
     * @return 站点活动配置
     */
    SitePromotion getBySiteId(Long siteId);

    /**
     * 记录推广数据
     *
     * @param sitePromotionConfig 站点配置
     * @param sysUser             当前会员
     * @param promoteUser         推广会员
     */
    void addPromotionDatas(SitePromotion sitePromotionConfig, SysUser sysUser, SysUser promoteUser);
}
