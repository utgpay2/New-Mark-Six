package com.central.backend.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.SiteOrderCo;
import com.central.backend.co.SiteOrderTotalCo;
import com.central.backend.co.SiteOrderUpdateCo;
import com.central.backend.mapper.SiteOrderMapper;
import com.central.backend.service.ISiteOrderService;
import com.central.backend.service.ISiteUserVipLogService;
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
public class SiteOrderServiceImpl extends SuperServiceImpl<SiteOrderMapper, SiteUserOrder> implements ISiteOrderService {
    @Autowired
    private ISiteUserVipLogService siteUserVipLogService;


    @Autowired
    private ISysUserService userService;


    @Autowired
    private IRptSiteSummaryService summaryService;



    @Override
    public PageResult<SiteUserOrder> findOrderList(SiteOrderCo params) {
        Page<SiteUserOrder> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<SiteUserOrder> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.eq(SiteUserOrder::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getUserName())){
            wrapper.eq(SiteUserOrder::getUserName, params.getUserName());
        }
        if (StringUtils.isNotBlank(params.getOrderNo())){
            wrapper.eq(SiteUserOrder::getOrderNo, params.getOrderNo());
        }

        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(SiteUserOrder::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(SiteUserOrder::getCreateTime, params.getEndTime());
        }

        if (params.getStatus()!=null){
            wrapper.eq(SiteUserOrder::getStatus, params.getStatus());
        }
        wrapper.orderByDesc(SiteUserOrder::getCreateTime);
        Page<SiteUserOrder> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<SiteUserOrder>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public BigDecimal findOrderTotal(SiteOrderTotalCo params) {
        return  baseMapper.findOrderTotal(params);
    }

    @Override
    public SiteUserVipLog findKpnSiteOrderInfo(Long id) {
        return  baseMapper.findKpnSiteOrderInfo(id);
    }

    @Override
    @Transactional
    public Result updateStatus(SiteOrderUpdateCo params) {
        Long id = params.getId();
        Integer state = params.getStatus();
        SiteUserOrder siteOrder = baseMapper.selectById(id);
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
        SiteUserVipLog siteUserVipLogInfo = baseMapper.findKpnSiteOrderInfo(params.getId());
        //修改会员vip到期时间
        Result<UserVipExpireVo> userResult = userService.updateUserVipExpire(siteUserVipLogInfo.getUserId(), siteUserVipLogInfo.getDays());
        if(userResult.getResp_code()!=0){
            return Result.failed("操作失败!");
        }
        UserVipExpireVo userInfo = userResult.getDatas();
        siteUserVipLogInfo.setBeforeExpire(userInfo.getBeforeExpire());
        siteUserVipLogInfo.setAfterExpire(userInfo.getAfterExpire());

        //添加vip日志
        String remark = StrUtil.format(VipChangeTypeEnum.getLogFormatByCode(VipChangeTypeEnum.CASH.getCode()), siteUserVipLogInfo.getAmount(), siteUserVipLogInfo.getCurrencyCode(), siteUserVipLogInfo.getDays());
        siteUserVipLogInfo.setRemark(remark);
        siteUserVipLogInfo.setType(VipChangeTypeEnum.CASH.getCode());
        siteUserVipLogInfo.setCreateBy(params.getUpdateBy());
        siteUserVipLogService.save(siteUserVipLogInfo);

        //添加运营报表数据
        summaryService.addSummaryNum(siteUserVipLogInfo);
        //修改订单状态
        boolean b  = super.updateById(siteOrder);

        return b  ?Result.succeed(siteOrder, "更新成功") : Result.failed("更新失败");
    }

    @Override
    public  List<SiteUserOrder> findOrderMobileList(List<Long> userIds) {
        return baseMapper.findOrderMobileList(userIds);
    }
}