package com.central.marksix.service;

import com.central.common.model.SiteUserVipLog;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.math.BigDecimal;
import java.util.Map;


public interface ISiteUserVipLogService extends ISuperService<SiteUserVipLog> {

    /**
     * 记录vip变动类型
     *
     * @param sysUser        会员
     * @param changeTypeCode 变动类型
     * @param days           变动天数
     * @param amount         金额(K币/金额)
     * @param currencyCode   币种(现金支付下有效)
     * @param params         其他参数
     */
    void addVipDaysChangeLog(SysUser sysUser, Integer changeTypeCode, Integer days, BigDecimal amount, String currencyCode, Map<String, Object> params);

    /**
     * 获取推广奖励vip天数
     * @param userId 会员id
     * @return
     */
    Integer getPromotionRewardVipDays(Long userId);
}