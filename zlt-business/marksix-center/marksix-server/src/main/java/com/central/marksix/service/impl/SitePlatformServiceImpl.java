package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.SitePlatform;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SitePlatformMapper;
import com.central.marksix.service.ISitePlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SitePlatformServiceImpl extends SuperServiceImpl<SitePlatformMapper, SitePlatform> implements ISitePlatformService {


    @Override
    public SitePlatform getBySiteId(Long sid) {
        String redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_PLATFORM_CONFIG_KEY, sid);
        SitePlatform sitePlatform = (SitePlatform) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(sitePlatform)) {
            sitePlatform = this.lambdaQuery().eq(SitePlatform::getSiteId, sid).one();
            if (ObjectUtil.isNotEmpty(sitePlatform)) {
                RedisRepository.setExpire(redisKey, MarksixConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return sitePlatform;
    }
}
