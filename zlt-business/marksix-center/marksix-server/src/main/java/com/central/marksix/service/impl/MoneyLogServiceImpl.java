package com.central.marksix.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.MoneyLog;
import com.central.common.model.SysUser;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.pay.SiteProduct;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.marksix.entity.PornPageResult;
import com.central.marksix.entity.vo.KbChangeRecordVo;
import com.central.marksix.entity.vo.SiteProductVo;
import com.central.marksix.enums.KbChangeTypeExtendEnum;
import com.central.marksix.mapper.MoneyLogMapper;
import com.central.marksix.service.IMoneyLogService;
import com.central.marksix.service.ISiteProductService;
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
public class MoneyLogServiceImpl extends SuperServiceImpl<MoneyLogMapper, MoneyLog> implements IMoneyLogService {

    @Autowired
    private ISiteProductService productService;

    @Override
    public void addKbChangeLog(SysUser sysUser, Integer type, BigDecimal rewardKb, Map<String, Object> params) {
        MoneyLog moneyLog = new MoneyLog();
        moneyLog.setUserId(sysUser.getId());
        moneyLog.setUserName(sysUser.getUsername());
        moneyLog.setOrderNo(RandomUtil.randomNumbers(20));
        moneyLog.setOrderType(type);
        moneyLog.setBeforeMoney(sysUser.getKBalance());
        moneyLog.setMoney(rewardKb);
        moneyLog.setAfterMoney(sysUser.getKBalance().add(rewardKb));
        moneyLog.setDate(DateUtil.formatDate(new Date()));
        //签到获取奖励
        if (type.equals(KbChangeTypeEnum.SIGN_REWARD.getType())) {
            moneyLog.setRemark("签到奖励kb:" + rewardKb.toPlainString());
        }
        //填入邀请码获取奖励
        else if (type.equals(KbChangeTypeEnum.FILL_INVITE_CODE.getType())) {
            moneyLog.setRemark(StrUtil.format("填写邀请码:{},获取奖励K币:{}", params.get("inviteCode"), rewardKb.toPlainString()));
        }
        //推广获取奖励
        else if (type.equals(KbChangeTypeEnum.PROMOTION.getType())) {
            moneyLog.setRemark(StrUtil.format("推广会员id:{},获取奖励K币: {}", params.get("userId"), rewardKb.toPlainString()));
        }
        //购买vip
        else if (type.equals(KbChangeTypeEnum.OPEN_VIP.getType())) {
            Long productId = (Long) params.get("productId");
            moneyLog.setProductId(productId);
            moneyLog.setRemark(StrUtil.format("购买vip产品id:{},消费K币: {}", productId, rewardKb.toPlainString()));
        }

        save(moneyLog);
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
        Page<MoneyLog> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<MoneyLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(MoneyLog::getUserId, MoneyLog::getOrderType, MoneyLog::getProductId, MoneyLog::getCreateTime, MoneyLog::getMoney);
        wrapper.eq(MoneyLog::getUserId, userId);
        wrapper.orderByDesc(MoneyLog::getCreateTime);

        Page<MoneyLog> moneyLogPage = this.baseMapper.selectPage(page, wrapper);
        List<MoneyLog> moneyLogPageRecords = moneyLogPage.getRecords();
        List<KbChangeRecordVo> changeRecordVos = new ArrayList<>();
        for (MoneyLog moneyLogPageRecord : moneyLogPageRecords) {
            KbChangeRecordVo changeRecordVo = new KbChangeRecordVo();
            changeRecordVo.setDateTime(moneyLogPageRecord.getCreateTime());
            changeRecordVo.setKbs(moneyLogPageRecord.getMoney());
            changeRecordVo.setAddOrSubTypeInt(KbChangeTypeExtendEnum.getKbChangeTypeEnumByType(moneyLogPageRecord.getOrderType()).getAddOrSub());
            changeRecordVo.setAddOrSubType(KbChangeTypeExtendEnum.getLanguageAddOrSubNameByType(moneyLogPageRecord.getOrderType()));
            if (KbChangeTypeExtendEnum.isSub(moneyLogPageRecord.getOrderType())) {
                changeRecordVo.setItemName(KbChangeTypeExtendEnum.getLanguageNameByType(moneyLogPageRecord.getOrderType()));
            } else {
                SiteProduct siteProduct = productService.getById(moneyLogPageRecord.getProductId());
                SiteProductVo siteProductVo = new SiteProductVo();
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



























