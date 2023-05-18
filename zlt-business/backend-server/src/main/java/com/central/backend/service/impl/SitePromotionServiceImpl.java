package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.SitePromotionMapper;
import com.central.backend.service.ISitePromotionService;
import com.central.common.model.SitePromotion;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SitePromotionServiceImpl extends SuperServiceImpl<SitePromotionMapper, SitePromotion> implements ISitePromotionService {

    @Override
    public SitePromotion findPromotionInfo(Long siteId) {
        LambdaQueryWrapper<SitePromotion> wrapper=new LambdaQueryWrapper<>();
        if (siteId!=null){
            wrapper.eq(SitePromotion::getSiteId,siteId);
        }
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Boolean saveOrUpdatePromotion(SitePromotion info) {
        boolean insert =false;
        if (info.getId()==null){
            insert = super.save(info);
        }else {
            SitePromotion promotion = baseMapper.selectById(info.getId());
            if (promotion != null) {
                //修改
                insert = super.updateById(info);
            }
        }
        return insert;
    }
}