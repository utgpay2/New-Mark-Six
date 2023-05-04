package com.central.porn.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSiteSign;
import com.central.common.model.KpnSiteSignDetail;
import com.central.common.model.SysUser;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.enums.VipChangeTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.vo.KpnSiteUserSignHistoryVo;
import com.central.porn.entity.vo.KpnSiteUserSignResultVo;
import com.central.porn.mapper.KpnSiteSignDetailMapper;
import com.central.porn.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KpnSiteSignDetailServiceImpl extends SuperServiceImpl<KpnSiteSignDetailMapper, KpnSiteSignDetail> implements IKpnSiteSignDetailService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IKpnSiteSignService siteSignService;

    @Autowired
    private IKpnSiteUserVipLogService siteUserVipLogService;

    @Autowired
    private IKpnMoneyLogService moneyLogService;

    @Override
    @Transactional
    public KpnSiteUserSignResultVo sign(SysUser sysUser, String date) {

        //连续签到
        /*String yesterday = DateUtil.formatDate(DateUtil.yesterday());
        KpnSiteSignDetail yesterdaySign = this.lambdaQuery().select(KpnSiteSignDetail::getDays).eq(KpnSiteSignDetail::getUserId, sysUser.getId()).eq(KpnSiteSignDetail::getSignDate, yesterday).orderByDesc(KpnSiteSignDetail::getId).last(PornConstants.Sql.LIMIT_ONLY_ONE).one();

        Integer signDays = 1;
        if (ObjectUtil.isNotEmpty(yesterdaySign)) {
            signDays = yesterdaySign.getDays() + 1;
        }*/

        //累计
        KpnSiteSignDetail lastSign = this.lambdaQuery()
                .select(KpnSiteSignDetail::getDays,KpnSiteSignDetail::getSignMonth)
                .eq(KpnSiteSignDetail::getUserId, sysUser.getId())
                .orderByDesc(KpnSiteSignDetail::getId)
                .last(PornConstants.Sql.LIMIT_ONLY_ONE).one();
        Integer signDays = 1;
        if (ObjectUtil.isNotEmpty(lastSign)) {
            //到一个月重新计算
            if(StrUtil.subBefore(date, PornConstants.Symbol.ARRIVE, true).equals(lastSign.getSignMonth())){
                signDays = lastSign.getDays() + 1;
            }
        }

        //获取中奖记录
        List<KpnSiteSign> kpnSiteSignConfigs = siteSignService.getBySiteId(sysUser.getSiteId());
        KpnSiteSign rewordSignConfig = null;
        for (KpnSiteSign kpnSiteSignConfig : kpnSiteSignConfigs) {
            if (kpnSiteSignConfig.getSignDays().equals(signDays)) {
                rewordSignConfig = kpnSiteSignConfig;
                break;
            }
        }

        //保存今日签到
        KpnSiteSignDetail todaySign = KpnSiteSignDetail.builder().siteId(sysUser.getSiteId()).siteCode(sysUser.getSiteCode()).siteName(sysUser.getSiteName()).userId(sysUser.getId()).username(sysUser.getUsername()).days(signDays).signMonth(StrUtil.subBefore(date, PornConstants.Symbol.ARRIVE, true)).signDate(date).build();

        KpnSiteUserSignResultVo resultVo = KpnSiteUserSignResultVo.builder().days(signDays).isReward(false).build();
        //未中奖
        if (ObjectUtil.isEmpty(rewordSignConfig)) {
            save(todaySign);
            return resultVo;
        }
        todaySign.setSiteSignId(rewordSignConfig.getId());
        todaySign.setIsReward(Boolean.TRUE);
        todaySign.setRewardVip(rewordSignConfig.getRewardVip());
        todaySign.setRewardKb(rewordSignConfig.getRewardKb());
        save(todaySign);

        //vip变动日志
        Map<String, Object> params = new HashMap<>();
        params.put("signDays", signDays);
        siteUserVipLogService.addVipDaysChangeLog(sysUser, VipChangeTypeEnum.SIGN.getCode(), rewordSignConfig.getRewardVip(), null, null, params);
        //会员vip日期
        userService.addRewardVipDays(sysUser, rewordSignConfig.getRewardVip());

        //K币变动
        userService.addRewardKb(sysUser, rewordSignConfig.getRewardKb());
        moneyLogService.addKbChangeLog(sysUser, KbChangeTypeEnum.SIGN_REWARD.getType(), rewordSignConfig.getRewardKb(), null);

        resultVo.setIsReward(Boolean.TRUE);
        resultVo.setRewardVip(rewordSignConfig.getRewardVip());
        resultVo.setRewardKb(rewordSignConfig.getRewardKb());
        return resultVo;
    }

    @Override
    public boolean checkHasSigned(Long userId, String date) {
        KpnSiteSignDetail signDetail = this.lambdaQuery()
                .select(KpnSiteSignDetail::getId)
                .eq(KpnSiteSignDetail::getUserId, userId)
                .eq(KpnSiteSignDetail::getSignMonth, StrUtil.subBefore(date, PornConstants.Symbol.ARRIVE, true))
                .eq(KpnSiteSignDetail::getSignDate, date)
                .one();
        return ObjectUtil.isNotEmpty(signDetail);
    }

    @Override
    public List<KpnSiteUserSignHistoryVo> getSignHistory(SysUser sysUser, String month) {
        List<KpnSiteSignDetail> signDetails = this.lambdaQuery()
                .select(KpnSiteSignDetail::getSignDate)
                .eq(KpnSiteSignDetail::getUserId, sysUser.getId())
                .eq(KpnSiteSignDetail::getSignMonth, month)
                .orderByAsc(KpnSiteSignDetail::getSignDate)
                .list();

        return signDetails.stream().map(signDetail -> KpnSiteUserSignHistoryVo.builder().date(signDetail.getSignDate()).signed(true).build()).collect(Collectors.toList());
    }

    @Override
    public Integer getUserSignDays(Long userId) {
        KpnSiteSignDetail signDetail = this.lambdaQuery().eq(KpnSiteSignDetail::getUserId, userId).orderByDesc(KpnSiteSignDetail::getSignDate).last(PornConstants.Sql.LIMIT_ONLY_ONE).one();
        return ObjectUtil.isEmpty(signDetail) ? 0 : signDetail.getDays();
    }
}
