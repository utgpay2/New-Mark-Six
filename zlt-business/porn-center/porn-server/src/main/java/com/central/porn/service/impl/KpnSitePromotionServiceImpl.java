package com.central.porn.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.central.common.model.KpnSitePromotion;
import com.central.common.model.SysUser;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.enums.VipChangeTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnSitePromotionMapper;
import com.central.porn.service.IKpnMoneyLogService;
import com.central.porn.service.IKpnSitePromotionService;
import com.central.porn.service.IKpnSiteUserVipLogService;
import com.central.porn.service.ISysUserService;
import com.central.porn.utils.PornUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class KpnSitePromotionServiceImpl extends SuperServiceImpl<KpnSitePromotionMapper, KpnSitePromotion> implements IKpnSitePromotionService {

    @Autowired
    private IKpnSiteUserVipLogService siteUserVipLogService;

    @Autowired
    @Lazy
    private ISysUserService sysUserService;

    @Autowired
    private IKpnMoneyLogService moneyLogService;

    @Override
    public KpnSitePromotion getBySiteId(Long siteId) {
        return this.lambdaQuery().eq(KpnSitePromotion::getSiteId, siteId).one();
    }

    @Override
    public void addPromotionDatas(KpnSitePromotion sitePromotionConfig, SysUser sysUser, SysUser promoteUser) {

        if (ObjectUtil.isNotEmpty(promoteUser)) {
            String inviteCode = promoteUser.getPromotionCode();
            //被邀请人 vip天数
            Integer beInvitedVip = sitePromotionConfig.getBeInvitedVip();
            if (ObjectUtil.isNotEmpty(beInvitedVip) && beInvitedVip > 0) {
                Map<String, Object> params = new HashMap<>();
                params.put("inviteCode", inviteCode);
                siteUserVipLogService.addVipDaysChangeLog(sysUser, VipChangeTypeEnum.FILL_INVITE_CODE.getCode(), beInvitedVip, null, null, params);
                sysUserService.addRewardVipDays(sysUser, beInvitedVip);
            }
            //被邀请人 K币数
            BigDecimal beInvitedKb = sitePromotionConfig.getBeInvitedKb();
            if (ObjectUtil.isNotEmpty(beInvitedKb) && PornUtil.isDecimalBigThanZero(beInvitedKb)) {
                sysUserService.addRewardKb(sysUser, beInvitedKb);
                Map<String, Object> params = new HashMap<>();
                params.put("inviteCode", inviteCode);
                moneyLogService.addKbChangeLog(sysUser, KbChangeTypeEnum.FILL_INVITE_CODE.getType(), beInvitedKb, params);
            }

            //推广人vip天数
            Integer inviteVip = sitePromotionConfig.getInviteVip();
            if (ObjectUtil.isNotEmpty(inviteVip) && inviteVip > 0) {
                Map<String, Object> params = new HashMap<>();
                params.put("userId", sysUser.getId());
                siteUserVipLogService.addVipDaysChangeLog(promoteUser, VipChangeTypeEnum.PROMOTION.getCode(), inviteVip, null, null, params);
                sysUserService.addRewardVipDays(promoteUser, inviteVip);
            }

            //推广人K币
            BigDecimal inviteKb = sitePromotionConfig.getInviteKb();
            BigDecimal kbDayLimit = sitePromotionConfig.getKbDayLimit();
            BigDecimal userPromoteTotalKbs = moneyLogService.getUserTodayPromoteTotalKbs(promoteUser.getId());
            if (!PornUtil.isDecimalGeThan(userPromoteTotalKbs, kbDayLimit)) {
                sysUserService.addRewardKb(promoteUser, inviteKb);

                Map<String, Object> params = new HashMap<>();
                params.put("userId", sysUser.getId());
                moneyLogService.addKbChangeLog(promoteUser, KbChangeTypeEnum.PROMOTION.getType(), inviteKb, params);
            }
        }
    }
}