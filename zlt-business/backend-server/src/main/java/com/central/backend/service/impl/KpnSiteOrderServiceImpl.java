package com.central.backend.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnSiteOrderCo;
import com.central.backend.co.KpnSiteOrderTotalCo;
import com.central.backend.co.KpnSiteOrderUpdateCo;
import com.central.backend.mapper.KpnSiteOrderMapper;
import com.central.backend.service.IKpnSiteOrderService;
import com.central.backend.service.IKpnSiteUserVipLogService;
import com.central.backend.service.IRptSiteSummaryService;
import com.central.backend.service.ISysUserService;
import com.central.backend.vo.UserVipExpireVo;
import com.central.common.model.*;
import com.central.common.model.enums.VipChangeTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
public class KpnSiteOrderServiceImpl extends SuperServiceImpl<KpnSiteOrderMapper, KpnSiteUserOrder> implements IKpnSiteOrderService {
    @Autowired
    private IKpnSiteUserVipLogService siteUserVipLogService;


    @Autowired
    private ISysUserService userService;


    @Autowired
    private IRptSiteSummaryService summaryService;



    @Override
    public PageResult<KpnSiteUserOrder> findOrderList(KpnSiteOrderCo params) {
        Page<KpnSiteUserOrder> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<KpnSiteUserOrder> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.eq(KpnSiteUserOrder::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getUserName())){
            wrapper.eq(KpnSiteUserOrder::getUserName, params.getUserName());
        }
        if (StringUtils.isNotBlank(params.getOrderNo())){
            wrapper.eq(KpnSiteUserOrder::getOrderNo, params.getOrderNo());
        }

        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(KpnSiteUserOrder::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(KpnSiteUserOrder::getCreateTime, params.getEndTime());
        }

        if (params.getStatus()!=null){
            wrapper.eq(KpnSiteUserOrder::getStatus, params.getStatus());
        }
        wrapper.orderByDesc(KpnSiteUserOrder::getCreateTime);
        Page<KpnSiteUserOrder> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<KpnSiteUserOrder>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public BigDecimal findOrderTotal(KpnSiteOrderTotalCo params) {
        return  baseMapper.findOrderTotal(params);
    }

    @Override
    public KpnSiteUserVipLog findKpnSiteOrderInfo(Long id) {
        return  baseMapper.findKpnSiteOrderInfo(id);
    }

    @Override
    @Transactional
    public Result updateStatus(KpnSiteOrderUpdateCo params) {
        Long id = params.getId();
        Integer state = params.getStatus();
        KpnSiteUserOrder siteOrder = baseMapper.selectById(id);
        if (siteOrder == null) {
            return Result.failed("数据不存在");
        }
        if(state==siteOrder.getStatus()){
            return Result.failed("不可重复修改状态!");
        }

        siteOrder.setStatus(state);
        if (StringUtils.isNotBlank(params.getRemark())) {
            siteOrder.setRemark(params.getRemark());
        }
        siteOrder.setUpdateBy(params.getUpdateBy());

        //查询订单信息
        KpnSiteUserVipLog KpnSiteUserVipLogInfo = baseMapper.findKpnSiteOrderInfo(params.getId());
        //修改会员vip到期时间
        Result<UserVipExpireVo> userResult = userService.updateUserVipExpire(KpnSiteUserVipLogInfo.getUserId(), KpnSiteUserVipLogInfo.getDays());
        if(userResult.getResp_code()!=0){
            return Result.failed("操作失败!");
        }
        UserVipExpireVo userInfo = userResult.getDatas();
        KpnSiteUserVipLogInfo.setBeforeExpire(userInfo.getBeforeExpire());
        KpnSiteUserVipLogInfo.setAfterExpire(userInfo.getAfterExpire());

        //添加vip日志
        String remark = StrUtil.format(VipChangeTypeEnum.getLogFormatByCode(VipChangeTypeEnum.CASH.getCode()),KpnSiteUserVipLogInfo.getAmount(), KpnSiteUserVipLogInfo.getCurrencyCode(), KpnSiteUserVipLogInfo.getDays());
        KpnSiteUserVipLogInfo.setRemark(remark);
        KpnSiteUserVipLogInfo.setType(VipChangeTypeEnum.CASH.getCode());
        KpnSiteUserVipLogInfo.setCreateBy(params.getUpdateBy());
        siteUserVipLogService.save(KpnSiteUserVipLogInfo);

        //添加运营报表数据
        summaryService.addSummaryNum(KpnSiteUserVipLogInfo);
        //修改订单状态
        boolean b  = super.updateById(siteOrder);

        return b  ?Result.succeed(siteOrder, "更新成功") : Result.failed("更新失败");
    }

    @Override
    public  List<KpnSiteUserOrder> findOrderMobileList(List<Long> userIds) {
        return baseMapper.findOrderMobileList(userIds);
    }
}