package com.central.porn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.central.common.model.KpnSite;
import com.central.common.model.KpnSitePlatform;
import com.central.common.model.SysUser;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.enums.VipChangeTypeEnum;
import com.central.common.model.pay.KpnSiteProduct;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.language.LanguageUtil;
import com.central.porn.entity.vo.KpnSiteProductVo;
import com.central.porn.mapper.KpnSiteProductMapper;
import com.central.porn.service.*;
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
public class KpnSiteProductServiceImpl extends SuperServiceImpl<KpnSiteProductMapper, KpnSiteProduct> implements IKpnSiteProductService {

    @Autowired
    private IKpnSitePlatformService sitePlatformService;

    @Autowired
    private IKpnSiteService siteService;

    @Override
    public List<KpnSiteProductVo> getSiteProducts(Long sid) {
        KpnSite kpnSite = siteService.getInfoById(sid);
        List<KpnSiteProduct> products = this.lambdaQuery()
                .eq(KpnSiteProduct::getSiteId, sid)
                .eq(KpnSiteProduct::getStatus, true)
                .orderByDesc(KpnSiteProduct::getSort)
                .orderByAsc(KpnSiteProduct::getId)
                .list();

        KpnSitePlatform sitePlatform = sitePlatformService.getBySiteId(sid);
        BigDecimal exchange = sitePlatform.getExchange();

        List<KpnSiteProductVo> resultVos = new ArrayList<>();
        for (KpnSiteProduct product : products) {
            KpnSiteProductVo siteProductVo = new KpnSiteProductVo();
            BeanUtil.copyProperties(product, siteProductVo);
            siteProductVo.setName(LanguageUtil.getLanguageName(siteProductVo));
            siteProductVo.setRealPrice(siteProductVo.getPrice().divide(exchange, 2, RoundingMode.CEILING));
            siteProductVo.setCurrency(kpnSite.getCurrencyCode());
            resultVos.add(siteProductVo);
        }

        return resultVos;
    }

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IKpnMoneyLogService moneyLogService;

    @Autowired
    private IKpnSiteUserVipLogService siteUserVipLogService;

    @Autowired
    private IRptSiteSummaryService siteSummaryService;

    @Override
    @Transactional
    public void buyUseKb(SysUser sysUser, KpnSiteProduct siteProduct) {
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
