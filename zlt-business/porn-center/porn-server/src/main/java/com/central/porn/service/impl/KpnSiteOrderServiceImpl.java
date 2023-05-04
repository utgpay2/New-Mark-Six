package com.central.porn.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.model.KpnSiteUserOrder;
import com.central.common.model.enums.CurrencyEnum;
import com.central.common.model.pay.KpnSiteProduct;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnSiteProductVo;
import com.central.porn.entity.vo.KpnSiteUserOrderVo;
import com.central.porn.enums.OrderStatusEnum;
import com.central.porn.mapper.KpnSiteOrderMapper;
import com.central.porn.service.IKpnSiteOrderService;
import com.central.porn.service.IKpnSiteProductService;
import com.central.porn.service.IKpnSiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class KpnSiteOrderServiceImpl extends SuperServiceImpl<KpnSiteOrderMapper, KpnSiteUserOrder> implements IKpnSiteOrderService {
    @Autowired
    private IKpnSiteService siteService;

    @Autowired
    private IKpnSiteProductService productService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public boolean isOrderNoExists(Long siteId, String orderNo) {
        KpnSiteUserOrder siteOrder = this.lambdaQuery().select(KpnSiteUserOrder::getSiteId).eq(KpnSiteUserOrder::getSiteId, siteId).eq(KpnSiteUserOrder::getOrderNo, orderNo).one();

        return ObjectUtil.isNotEmpty(siteOrder);
    }

    @Override
    public PornPageResult<KpnSiteUserOrderVo> getUserOrderRecords(Long sid, Long userId, Integer currPage, Integer pageSize) throws Exception {
        Page<KpnSiteUserOrder> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<KpnSiteUserOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KpnSiteUserOrder::getUserId, userId);
        wrapper.orderByDesc(KpnSiteUserOrder::getCreateTime);

        Page<KpnSiteUserOrder> userOrderPage = this.baseMapper.selectPage(page, wrapper);
        List<KpnSiteUserOrderVo> userOrderVos = new ArrayList<>();

        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(userOrderPage.getRecords().size());
        for (KpnSiteUserOrder userOrder : userOrderPage.getRecords()) {
            KpnSiteUserOrderVo userOrderVo = new KpnSiteUserOrderVo();
            userOrderVo.setCreateTime(userOrder.getCreateTime());
            taskExecutor.execute(() -> {
                KpnSiteProduct siteProduct = productService.getById(userOrder.getProductId());
                KpnSiteProductVo siteProductVo = new KpnSiteProductVo();
                BeanUtil.copyProperties(siteProduct, siteProductVo);
                userOrderVo.setProductName(LanguageUtil.getLanguageName(siteProductVo));

                KpnSite siteInfo = siteService.getInfoById(sid);
                String currencySymbol = CurrencyEnum.getSymbolByCode(siteInfo.getCurrencyCode());
                userOrderVo.setAmount(userOrder.getProductPrice() + PornConstants.Str.SPACE + currencySymbol);

                countDownLatch.countDown();
            });
            userOrderVo.setStatus(userOrder.getStatus());
            userOrderVo.setResult(OrderStatusEnum.getNameByCode(userOrder.getStatus()));
            userOrderVo.setRemark(userOrder.getRemark());
            userOrderVos.add(userOrderVo);
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.info("耗时:" + (end - start));

        Long total = page.getTotal();
        Integer totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        return PornPageResult.<KpnSiteUserOrderVo>builder()
                .data(userOrderVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }
}