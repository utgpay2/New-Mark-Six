package com.central.marksix.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.SiteSign;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SiteSignMapper;
import com.central.marksix.service.ISiteSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class SiteSignServiceImpl extends SuperServiceImpl<SiteSignMapper, SiteSign> implements ISiteSignService {

    @Override
    public List<SiteSign> getBySiteId(Long sid) {
        String siteSignConfigRedisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_SIGN_CONFIG, sid);
        List<SiteSign> siteSigns = (ArrayList) RedisRepository.get(siteSignConfigRedisKey);

        if (CollectionUtil.isEmpty(siteSigns)) {
            siteSigns = this.lambdaQuery().eq(SiteSign::getSiteId, sid).list();
            if (CollectionUtil.isNotEmpty(siteSigns)) {
                RedisRepository.set(siteSignConfigRedisKey, siteSigns);
            }
        }
        return siteSigns;
    }
    @Override
    public List<SiteSign> findSignList(Long siteId) {
        LambdaQueryWrapper<SiteSign> wrapper=new LambdaQueryWrapper<>();
        if (siteId!=null){
            wrapper.eq(SiteSign::getSiteId,siteId);
        }
        return baseMapper.selectList(wrapper);
    }
}