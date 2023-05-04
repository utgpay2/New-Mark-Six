package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.KpnSitePlatformMapper;
import com.central.backend.service.IKpnSitePlatformService;
import com.central.common.model.KpnSitePlatform;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KpnSitePlatformServiceImpl extends SuperServiceImpl<KpnSitePlatformMapper, KpnSitePlatform> implements IKpnSitePlatformService {


    @Override
    public KpnSitePlatform findPromotionInfo(Long siteId) {
        LambdaQueryWrapper<KpnSitePlatform> wrapper=new LambdaQueryWrapper<>();
        if (siteId!=null){
            wrapper.eq(KpnSitePlatform::getSiteId,siteId);
        }
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Boolean saveOrUpdatePlatform(KpnSitePlatform info) {
        boolean insert =false;
        if (info.getId()==null){
            insert = super.save(info);
        }else {
            KpnSitePlatform platform = baseMapper.selectById(info.getId());
            if (platform != null) {
                if (info.getExchange() !=null ) {
                    platform.setExchange(info.getExchange());
                }
                if (StringUtils.isNotBlank(info.getLostDomain())) {
                    platform.setLostDomain(info.getLostDomain());
                }
                if (info.getTryTime() !=null ) {
                    platform.setTryTime(info.getTryTime());
                }
                platform.setUpdateBy(info.getUpdateBy());
                insert = super.updateById(platform);
            }

        }
        return insert;
    }
}