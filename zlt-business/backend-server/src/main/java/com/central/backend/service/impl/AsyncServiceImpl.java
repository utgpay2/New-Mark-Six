package com.central.backend.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.ISiteService;
import com.central.common.constant.MarksixConstants;
import com.central.common.redis.template.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {

    @Autowired
    @Lazy
    private ISiteService siteService;


    @Async
    @Override
    public void setVipExpire(Date newVipExpire, Long userId) {
        try {
            long expireInSeconds = (newVipExpire.getTime() - new Date().getTime()) / 1000;
            String vipExpireKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_VIP_EXPIRE, userId);
            RedisRepository.setExpire(vipExpireKey, DateUtil.formatDateTime(newVipExpire), expireInSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteInfoCache(Long sid) {
        try {
            RedisRepository.delete(MarksixConstants.RedisKey.KPN_SITE_LIST_KEY);
            if (ObjectUtil.isNotEmpty(sid)) {
                RedisRepository.delete(StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_INFO_KEY, sid));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteLinesCache() {
        try {
            RedisRepository.delete(MarksixConstants.RedisKey.KPN_LINE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    @Async
    @Override
    public void deleteSitePlatformCache(Long sid) {
        try {
            String redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_PLATFORM_CONFIG_KEY, sid);
            RedisRepository.delete(redisKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Async
    @Override
    public void deleteSiteSignConfigCache(Long sid) {
        try {
            String redisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_SIGN_CONFIG, sid);
            RedisRepository.delete(redisKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
