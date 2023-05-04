package com.central.porn.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSitePlatform;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnSitePlatformMapper;
import com.central.porn.service.IKpnSitePlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KpnSitePlatformServiceImpl extends SuperServiceImpl<KpnSitePlatformMapper, KpnSitePlatform> implements IKpnSitePlatformService {


    @Override
    public KpnSitePlatform getBySiteId(Long sid) {
        String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_PLATFORM_CONFIG_KEY, sid);
        KpnSitePlatform sitePlatform = (KpnSitePlatform) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(sitePlatform)) {
            sitePlatform = this.lambdaQuery().eq(KpnSitePlatform::getSiteId, sid).one();
            if (ObjectUtil.isNotEmpty(sitePlatform)) {
                RedisRepository.setExpire(redisKey, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return sitePlatform;
    }
}
