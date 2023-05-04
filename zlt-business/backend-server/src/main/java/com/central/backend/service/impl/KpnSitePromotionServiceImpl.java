package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.KpnSitePromotionMapper;
import com.central.backend.service.IKpnSitePromotionService;
import com.central.common.model.KpnSitePromotion;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KpnSitePromotionServiceImpl extends SuperServiceImpl<KpnSitePromotionMapper, KpnSitePromotion> implements IKpnSitePromotionService {

    @Override
    public KpnSitePromotion findPromotionInfo(Long siteId) {
        LambdaQueryWrapper<KpnSitePromotion> wrapper=new LambdaQueryWrapper<>();
        if (siteId!=null){
            wrapper.eq(KpnSitePromotion::getSiteId,siteId);
        }
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Boolean saveOrUpdatePromotion(KpnSitePromotion info) {
        boolean insert =false;
        if (info.getId()==null){
            insert = super.save(info);
        }else {
            KpnSitePromotion promotion = baseMapper.selectById(info.getId());
            if (promotion != null) {
                //修改
                insert = super.updateById(info);
            }
        }
        return insert;
    }
}