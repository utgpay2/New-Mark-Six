package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.proxy.center.mapper.SitePlatformMapper;
import com.proxy.center.service.ISitePlatformService;
import com.central.common.model.SitePlatform;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SitePlatformServiceImpl extends SuperServiceImpl<SitePlatformMapper, SitePlatform> implements ISitePlatformService {


    @Override
    public SitePlatform findPromotionInfo(Long siteId) {
        LambdaQueryWrapper<SitePlatform> wrapper=new LambdaQueryWrapper<>();
        if (siteId!=null){
            wrapper.eq(SitePlatform::getSiteId,siteId);
        }
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Boolean saveOrUpdatePlatform(SitePlatform info) {
        boolean insert =false;
        if (info.getId()==null){
            insert = super.save(info);
        }else {
            SitePlatform platform = baseMapper.selectById(info.getId());
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