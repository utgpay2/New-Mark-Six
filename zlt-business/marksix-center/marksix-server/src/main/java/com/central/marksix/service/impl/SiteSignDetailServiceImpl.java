package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.SiteSign;
import com.central.common.model.SiteSignDetail;
import com.central.common.model.SysUser;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.enums.VipChangeTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.SiteUserSignHistoryVo;
import com.central.marksix.entity.vo.SiteUserSignResultVo;
import com.central.marksix.mapper.SiteSignDetailMapper;
import com.central.marksix.service.*;
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
public class SiteSignDetailServiceImpl extends SuperServiceImpl<SiteSignDetailMapper, SiteSignDetail> implements ISiteSignDetailService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISiteSignService siteSignService;

    @Autowired
    private ISiteUserVipLogService siteUserVipLogService;

    @Autowired
    private IMoneyLogService moneyLogService;

    @Override
    @Transactional
    public SiteUserSignResultVo sign(SysUser sysUser, String date) {

        //连续签到
        /*String yesterday = DateUtil.formatDate(DateUtil.yesterday());
        SiteSignDetail yesterdaySign = this.lambdaQuery().select(SiteSignDetail::getDays).eq(SiteSignDetail::getUserId, sysUser.getId()).eq(SiteSignDetail::getSignDate, yesterday).orderByDesc(SiteSignDetail::getId).last(MarksixConstants.Sql.LIMIT_ONLY_ONE).one();

        Integer signDays = 1;
        if (ObjectUtil.isNotEmpty(yesterdaySign)) {
            signDays = yesterdaySign.getDays() + 1;
        }*/

        //累计
        SiteSignDetail lastSign = this.lambdaQuery()
                .select(SiteSignDetail::getDays, SiteSignDetail::getSignMonth)
                .eq(SiteSignDetail::getUserId, sysUser.getId())
                .orderByDesc(SiteSignDetail::getId)
                .last(MarksixConstants.Sql.LIMIT_ONLY_ONE).one();
        Integer signDays = 1;
        if (ObjectUtil.isNotEmpty(lastSign)) {
            //到一个月重新计算
            if(StrUtil.subBefore(date, MarksixConstants.Symbol.ARRIVE, true).equals(lastSign.getSignMonth())){
                signDays = lastSign.getDays() + 1;
            }
        }

        //获取中奖记录
        List<SiteSign> siteSignConfigs = siteSignService.getBySiteId(sysUser.getSiteId());
        SiteSign rewordSignConfig = null;
        for (SiteSign siteSignConfig : siteSignConfigs) {
            if (siteSignConfig.getSignDays().equals(signDays)) {
                rewordSignConfig = siteSignConfig;
                break;
            }
        }

        //保存今日签到
        SiteSignDetail todaySign = SiteSignDetail.builder().siteId(sysUser.getSiteId()).siteCode(sysUser.getSiteCode()).siteName(sysUser.getSiteName()).userId(sysUser.getId()).username(sysUser.getUsername()).days(signDays).signMonth(StrUtil.subBefore(date, MarksixConstants.Symbol.ARRIVE, true)).signDate(date).build();

        SiteUserSignResultVo resultVo = SiteUserSignResultVo.builder().days(signDays).isReward(false).build();
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
        SiteSignDetail signDetail = this.lambdaQuery()
                .select(SiteSignDetail::getId)
                .eq(SiteSignDetail::getUserId, userId)
                .eq(SiteSignDetail::getSignMonth, StrUtil.subBefore(date, MarksixConstants.Symbol.ARRIVE, true))
                .eq(SiteSignDetail::getSignDate, date)
                .one();
        return ObjectUtil.isNotEmpty(signDetail);
    }

    @Override
    public List<SiteUserSignHistoryVo> getSignHistory(SysUser sysUser, String month) {
        List<SiteSignDetail> signDetails = this.lambdaQuery()
                .select(SiteSignDetail::getSignDate)
                .eq(SiteSignDetail::getUserId, sysUser.getId())
                .eq(SiteSignDetail::getSignMonth, month)
                .orderByAsc(SiteSignDetail::getSignDate)
                .list();

        return signDetails.stream().map(signDetail -> SiteUserSignHistoryVo.builder().date(signDetail.getSignDate()).signed(true).build()).collect(Collectors.toList());
    }

    @Override
    public Integer getUserSignDays(Long userId) {
        SiteSignDetail signDetail = this.lambdaQuery().eq(SiteSignDetail::getUserId, userId).orderByDesc(SiteSignDetail::getSignDate).last(MarksixConstants.Sql.LIMIT_ONLY_ONE).one();
        return ObjectUtil.isEmpty(signDetail) ? 0 : signDetail.getDays();
    }
}
