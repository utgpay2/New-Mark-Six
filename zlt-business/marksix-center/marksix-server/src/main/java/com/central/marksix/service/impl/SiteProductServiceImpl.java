package com.central.marksix.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.central.common.model.Site;
import com.central.common.model.SitePlatform;
import com.central.common.model.SysUser;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.enums.VipChangeTypeEnum;
import com.central.common.model.pay.SiteProduct;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.marksix.entity.vo.SiteProductVo;
import com.central.marksix.mapper.SiteProductMapper;
import com.central.marksix.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SiteProductServiceImpl extends SuperServiceImpl<SiteProductMapper, SiteProduct> implements ISiteProductService {

    @Autowired
    private ISitePlatformService sitePlatformService;

    @Autowired
    private ISiteService siteService;

    @Override
    public List<SiteProductVo> getSiteProducts(Long sid) {
        Site site = siteService.getInfoById(sid);
        List<SiteProduct> products = this.lambdaQuery()
                .eq(SiteProduct::getSiteId, sid)
                .eq(SiteProduct::getStatus, true)
                .orderByDesc(SiteProduct::getSort)
                .orderByAsc(SiteProduct::getId)
                .list();

        SitePlatform sitePlatform = sitePlatformService.getBySiteId(sid);
        BigDecimal exchange = sitePlatform.getExchange();

        List<SiteProductVo> resultVos = new ArrayList<>();
        for (SiteProduct product : products) {
            SiteProductVo siteProductVo = new SiteProductVo();
            BeanUtil.copyProperties(product, siteProductVo);
            siteProductVo.setName(LanguageUtil.getLanguageName(siteProductVo));
            siteProductVo.setRealPrice(siteProductVo.getPrice().divide(exchange, 2, RoundingMode.CEILING));
            siteProductVo.setCurrency(site.getCurrencyCode());
            resultVos.add(siteProductVo);
        }

        return resultVos;
    }

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IMoneyLogService moneyLogService;

    @Autowired
    private ISiteUserVipLogService siteUserVipLogService;

    @Autowired
    private IRptSiteSummaryService siteSummaryService;

    @Override
    @Transactional
    public void buyUseKb(SysUser sysUser, SiteProduct siteProduct) {
        BigDecimal kbPrice = siteProduct.getPrice().negate();
        //扣除K币
        sysUserService.addRewardKb(sysUser, kbPrice);
        //K币账变
        Map<String, Object> params = new HashMap<>();
        params.put("productId", siteProduct.getId());
        moneyLogService.addKbChangeLog(sysUser, KbChangeTypeEnum.OPEN_VIP.getType(), kbPrice, params);
        //vip开通/续期
        Integer vipDays = siteProduct.getValidDays();
        sysUserService.addRewardVipDays(sysUser, vipDays);
        params = new HashMap<>();
        params.put("kbs", siteProduct.getPrice());
        siteUserVipLogService.addVipDaysChangeLog(sysUser, VipChangeTypeEnum.KB.getCode(), vipDays, null, null, params);

        if (!sysUser.getVip()) {
            //记录站点新增vip数
            siteSummaryService.addNewVipNum(sysUser.getSiteId(), sysUser.getSiteCode(), sysUser.getSiteName());
        }
    }
}
