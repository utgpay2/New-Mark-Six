package com.central.porn.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.model.KpnSitePromotion;
import com.central.common.model.SysUser;
import com.central.common.model.enums.UserRegTypeEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.SysUserMapper;
import com.central.porn.service.IKpnSitePromotionService;
import com.central.porn.service.IKpnSiteService;
import com.central.porn.service.IRptSiteSummaryService;
import com.central.porn.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * year
 */
@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private IKpnSiteService siteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private IKpnSitePromotionService promotionConfigService;

    @Autowired
    private IRptSiteSummaryService siteSummaryService;


    @Override
    public SysUser getByInviteCode(String inviteCode) {
        return this.lambdaQuery()
                .eq(SysUser::getType, UserTypeEnum.APP.name())
                .eq(SysUser::getPromotionCode, inviteCode)
                .one();
    }

    @Override
    public void addRewardVipDays(SysUser sysUser, Integer vipDays) {
        Date vipExpire = sysUser.getVipExpire() == null ? DateUtil.offsetDay(new Date(), vipDays) : DateUtil.offsetDay(sysUser.getVipExpire(), vipDays);
        this.lambdaUpdate().eq(SysUser::getId, sysUser.getId())
                .set(SysUser::getVipExpire, vipExpire)
                .set(SysUser::getVip, true)
                .update();

        long expireInSeconds = (vipExpire.getTime() - new Date().getTime()) / 1000;
        String vipExpireKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_VIP_EXPIRE, sysUser.getId());
        RedisRepository.setExpire(vipExpireKey, DateUtil.formatDateTime(vipExpire), expireInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void addRewardKb(SysUser sysUser, BigDecimal rewardKb) {
        this.lambdaUpdate().eq(SysUser::getId, sysUser.getId())
                .setSql("`k_balance` = `k_balance` + " + rewardKb)
                .update();
    }

    @Override
    public Integer getPromotionMemberCount(String promotionCode) {

        return this.lambdaQuery().eq(SysUser::getInviteCode, promotionCode).count();
    }

    @Override
    @Transactional
    public void register(Long sid, SysUser promoteUser, String nickName, String username, String password) {
        KpnSite siteInfo = siteService.getInfoById(sid);

        SysUser newAppUser = new SysUser();
        newAppUser.setSiteId(sid);
        newAppUser.setSiteCode(siteInfo.getCode());
        newAppUser.setSiteName(siteInfo.getName());
        newAppUser.setUsername(username);
        newAppUser.setNickname(nickName);
        newAppUser.setType(UserTypeEnum.APP.name());
        newAppUser.setPassword(passwordEncoder.encode(password));
        newAppUser.setIsReg(UserRegTypeEnum.SELF_REG.getType());
        newAppUser.setKBalance(BigDecimal.ZERO);

        if (ObjectUtil.isNotEmpty(promoteUser)) {
            newAppUser.setParentId(promoteUser.getId());
            newAppUser.setParentName(promoteUser.getUsername());
            newAppUser.setInviteCode(promoteUser.getPromotionCode());
        }
        boolean succeed = false;
        int tryTimes = 1;
        do {
            String promotionCode = RandomUtil.randomString(PornConstants.Str.RANDOM_BASE_STR, PornConstants.Numeric.INVITE_CODE_LENGTH);
            try {
                newAppUser.setPromotionCode(promotionCode);
                succeed = save(newAppUser);
            } catch (Exception e) {
                log.error(promotionCode + " : " + e.getMessage(), e);
                if (tryTimes++ >= 3) {
                    throw e;
                }
            }
        } while (!succeed);

        //站点推广活动配置
        KpnSitePromotion sitePromotionConfig = promotionConfigService.getBySiteId(sid);
        if (ObjectUtil.isEmpty(sitePromotionConfig)) {
            return;
        }
        promotionConfigService.addPromotionDatas(sitePromotionConfig, newAppUser, promoteUser);

        //增加站点统计-新增会员数
        siteSummaryService.addNewUserNum(sid, siteInfo.getCode(), siteInfo.getName());

        //记录站点新增vip数
        if(ObjectUtil.isNotEmpty(promoteUser)){
            siteSummaryService.addNewVipNum(siteInfo.getId(), siteInfo.getCode(), siteInfo.getName());
            if(!promoteUser.getVip()){
                siteSummaryService.addNewVipNum(siteInfo.getId(), siteInfo.getCode(), siteInfo.getName());
            }
        }

    }

    @Override
    @Transactional
    public void saveInviteCode(Long sid, Long userId, SysUser promoteUser, String inviteCode) {
        SysUser sysUser = getById(userId);
        sysUser.setInviteCode(inviteCode);
        sysUser.setParentId(promoteUser.getId());
        sysUser.setParentName(promoteUser.getUsername());
        saveOrUpdate(sysUser);

        //推广活动
        KpnSitePromotion sitePromotionConfig = promotionConfigService.getBySiteId(sid);
        if (ObjectUtil.isEmpty(sitePromotionConfig)) {
            return;
        }

        promotionConfigService.addPromotionDatas(sitePromotionConfig, sysUser, promoteUser);

        //记录站点新增vip数
        if (!sysUser.getVip()) {
            siteSummaryService.addNewVipNum(sysUser.getSiteId(), sysUser.getSiteCode(), sysUser.getSiteName());
        }
    }

    @Async
    @Override
    public void updateVipExpire(Long userId) {
        this.lambdaUpdate()
                .eq(SysUser::getId, userId)
                .set(SysUser::getVip, false)
                .set(SysUser::getVipExpire, null)
                .update();
    }

}

























