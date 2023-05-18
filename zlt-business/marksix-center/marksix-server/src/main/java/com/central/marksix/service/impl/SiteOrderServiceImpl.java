package com.central.marksix.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.Site;
import com.central.common.model.SiteUserOrder;
import com.central.common.model.enums.CurrencyEnum;
import com.central.common.model.pay.SiteProduct;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.marksix.entity.PornPageResult;
import com.central.marksix.entity.vo.SiteProductVo;
import com.central.marksix.entity.vo.SiteUserOrderVo;
import com.central.marksix.enums.OrderStatusEnum;
import com.central.marksix.mapper.SiteOrderMapper;
import com.central.marksix.service.ISiteOrderService;
import com.central.marksix.service.ISiteProductService;
import com.central.marksix.service.ISiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class SiteOrderServiceImpl extends SuperServiceImpl<SiteOrderMapper, SiteUserOrder> implements ISiteOrderService {
    @Autowired
    private ISiteService siteService;

    @Autowired
    private ISiteProductService productService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public boolean isOrderNoExists(Long siteId, String orderNo) {
        SiteUserOrder siteOrder = this.lambdaQuery().select(SiteUserOrder::getSiteId).eq(SiteUserOrder::getSiteId, siteId).eq(SiteUserOrder::getOrderNo, orderNo).one();

        return ObjectUtil.isNotEmpty(siteOrder);
    }

    @Override
    public PornPageResult<SiteUserOrderVo> getUserOrderRecords(Long sid, Long userId, Integer currPage, Integer pageSize) throws Exception {
        Page<SiteUserOrder> page = new Page<>(currPage, pageSize);
        LambdaQueryWrapper<SiteUserOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SiteUserOrder::getUserId, userId);
        wrapper.orderByDesc(SiteUserOrder::getCreateTime);

        Page<SiteUserOrder> userOrderPage = this.baseMapper.selectPage(page, wrapper);
        List<SiteUserOrderVo> userOrderVos = new ArrayList<>();

        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(userOrderPage.getRecords().size());
        for (SiteUserOrder userOrder : userOrderPage.getRecords()) {
            SiteUserOrderVo userOrderVo = new SiteUserOrderVo();
            userOrderVo.setCreateTime(userOrder.getCreateTime());
            taskExecutor.execute(() -> {
                SiteProduct siteProduct = productService.getById(userOrder.getProductId());
                SiteProductVo siteProductVo = new SiteProductVo();
                BeanUtil.copyProperties(siteProduct, siteProductVo);
                userOrderVo.setProductName(LanguageUtil.getLanguageName(siteProductVo));

                Site siteInfo = siteService.getInfoById(sid);
                String currencySymbol = CurrencyEnum.getSymbolByCode(siteInfo.getCurrencyCode());
                userOrderVo.setAmount(userOrder.getProductPrice() + MarksixConstants.Str.SPACE + currencySymbol);

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

        return PornPageResult.<SiteUserOrderVo>builder()
                .data(userOrderVos)
                .currPage(currPage)
                .pageSize(pageSize)
                .count(total)
                .totalPage(totalPage)
                .build();
    }
}