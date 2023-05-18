package com.central.marksix.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.Site;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SiteMapper;
import com.central.marksix.service.ISiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SiteServiceImpl extends SuperServiceImpl<SiteMapper, Site> implements ISiteService {

    @Override
    public List<Site> getList() {
        String redisKey = MarksixConstants.RedisKey.KPN_SITE_LIST_KEY;
        List<Site> sites = (ArrayList) RedisRepository.get(redisKey);
        if (CollectionUtil.isEmpty(sites)) {
            sites = this.lambdaQuery().eq(Site::getStatus, true).list();
            if (CollectionUtil.isNotEmpty(sites)) {
                RedisRepository.setExpire(redisKey, sites, MarksixConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return sites;
    }

    @Override
    public Site getInfoByReferer(String referer) {
        Site site = this.lambdaQuery().like(Site::getDomains, referer).one();
        return site;
    }

    @Override
    public Site getInfoById(Long sid) {
        String siteKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_INFO_KEY, sid);
        Site site = (Site) RedisRepository.get(siteKey);
        if (ObjectUtil.isEmpty(site)) {
            site = getById(sid);
            if (ObjectUtil.isNotEmpty(site)) {
                RedisRepository.setExpire(siteKey, site, MarksixConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return site;
    }
}
