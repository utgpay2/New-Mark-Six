package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.KpnMoneyLog;
import com.central.common.model.SysUser;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.pay.KpnSiteProduct;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KbChangeRecordVo;
import com.central.porn.entity.vo.KpnSiteProductVo;
import com.central.porn.enums.KbChangeTypeExtendEnum;
import com.central.porn.mapper.KpnMoneyLogMapper;
import com.central.porn.service.IKpnMoneyLogService;
import com.central.porn.service.IKpnSiteProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class KpnMoneyLogServiceImpl extends SuperServiceImpl<KpnMoneyLogMapper, KpnMoneyLog> implements IKpnMoneyLogService {

    @Autowired
    private IKpnSiteProductService productService;

    @Override
    public void addKbChangeLog(SysUser sysUser, Integer type, BigDecimal rewardKb, Map<String, Object> params) {
        KpnMoneyLog kpnMoneyLog = new KpnMoneyLog();
        kpnMoneyLog.setUserId(sysUser.getId());
        kpnMoneyLog.setUserName(sysUser.getUsername());
        kpnMoneyLog.setOrderNo(RandomUtil.randomNumbers(20));
        kpnMoneyLog.setOrderType(type);
        kpnMoneyLog.setBeforeMoney(sysUser.getKBalance());
        kpnMoneyLog.setMoney(rewardKb);
        kpnMoneyLog.setAfterMoney(sysUser.getKBalance().add(rewardKb));
        kpnMoneyLog.setDate(DateUtil.formatDate(new Date()));
        //签到获取奖励
        if (type.equals(KbChangeTypeEnum.SIGN_REWARD.getType())) {
            kpnMoneyLog.setRemark("签到奖励kb:" + rewardKb.toPlainString());
        }
        //填入邀请码获取奖励
        else if (type.equals(KbChangeTypeEnum.FILL_INVITE_CODE.getType())) {
            kpnMoneyLog.setRemark(StrUtil.format("填写邀请码:{},获取奖励K币:{}", params.get("inviteCode"), rewardKb.toPlainString()));
        }
        //推广获取奖励
        else if (type.equals(KbChangeTypeEnum.PROMOTION.getType())) {
            kpnMoneyLog.setRemark(StrUtil.format("推广会员id:{},获取奖励K币: {}", params.get("userId"), rewardKb.toPlainString()));
        }
        //购买vip
        else if (type.equals(KbChangeTypeEnum.OPEN_VIP.getType())) {
            Long productId = (Long) params.get("productId");
            kpnMoneyLog.setProductId(productId);
            kpnMoneyLog.setRemark(StrUtil.format("购买vip产品id:{},消费K币: {}", productId, rewardKb.toPlainString()));
        }

        save(kpnMoneyLog);
    }

    @Override
    public BigDecimal getPromotionRewardTotalKbs(Long userId) {
        return this.baseMapper.getRewardKbsByKbChangeType(userId, KbChangeTypeEnum.PROMOTION.getType());
    }

    @Override
    public BigDecimal getUserTodayPromoteTotalKbs(Long userId) {
        String today = DateUtil.formatDate(new Date());
        Integer kbChangeTypeCode = KbChangeTypeEnum.PROMOTION.getType();
        return this.baseMapper.getUserTodayPromoteTotalKb(userId, today, kbChangeTypeCode);
    }

    @Override
    public PornPageResult<KbChangeRecordVo> getByUserId(Long userId, Integer currPage, Integer pageSize) {
        Page<KpnMoneyLog> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<KpnMoneyLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(KpnMoneyLog::getUserId, KpnMoneyLog::getOrderType, KpnMoneyLog::getProductId, KpnMoneyLog::getCreateTime, KpnMoneyLog::getMoney);
        wrapper.eq(KpnMoneyLog::getUserId, userId);
        wrapper.orderByDesc(KpnMoneyLog::getCreateTime);

        Page<KpnMoneyLog> moneyLogPage = this.baseMapper.selectPage(page, wrapper);
        List<KpnMoneyLog> moneyLogPageRecords = moneyLogPage.getRecords();
        List<KbChangeRecordVo> changeRecordVos = new ArrayList<>();
        for (KpnMoneyLog moneyLogPageRecord : moneyLogPageRecords) {
            KbChangeRecordVo changeRecordVo = new KbChangeRecordVo();
            changeRecordVo.setDateTime(moneyLogPageRecord.getCreateTime());
            changeRecordVo.setKbs(moneyLogPageRecord.getMoney());
            changeRecordVo.setAddOrSubTypeInt(KbChangeTypeExtendEnum.getKbChangeTypeEnumByType(moneyLogPageRecord.getOrderType()).getAddOrSub());
            changeRecordVo.setAddOrSubType(KbChangeTypeExtendEnum.getLanguageAddOrSubNameByType(moneyLogPageRecord.getOrderType()));
            if (KbChangeTypeExtendEnum.isSub(moneyLogPageRecord.getOrderType())) {
                changeRecordVo.setItemName(KbChangeTypeExtendEnum.getLanguageNameByType(moneyLogPageRecord.getOrderType()));
            } else {
                KpnSiteProduct siteProduct = productService.getById(moneyLogPageRecord.getProductId());
                KpnSiteProductVo siteProductVo = new KpnSiteProductVo();
                BeanUtil.copyProperties(siteProduct, siteProductVo);
                changeRecordVo.setItemName(LanguageUtil.getLanguageName(siteProductVo));
            }

            changeRecordVos.add(changeRecordVo);
        }

        Long total = page.getTotal();
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        return PornPageResult.<KbChangeRecordVo>builder()
                .data(changeRecordVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }
}



























