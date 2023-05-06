package com.central.marksix.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.KpnSiteSign;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.KpnSiteSignMapper;
import com.central.marksix.service.IKpnSiteSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class KpnSiteSignServiceImpl extends SuperServiceImpl<KpnSiteSignMapper, KpnSiteSign> implements IKpnSiteSignService {

    @Override
    public List<KpnSiteSign> getBySiteId(Long sid) {
        String siteSignConfigRedisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_SIGN_CONFIG, sid);
        List<KpnSiteSign> kpnSiteSigns = (ArrayList) RedisRepository.get(siteSignConfigRedisKey);

        if (CollectionUtil.isEmpty(kpnSiteSigns)) {
            kpnSiteSigns = this.lambdaQuery().eq(KpnSiteSign::getSiteId, sid).list();
            if (CollectionUtil.isNotEmpty(kpnSiteSigns)) {
                RedisRepository.set(siteSignConfigRedisKey, kpnSiteSigns);
            }
        }
        return kpnSiteSigns;
    }
    @Override
    public List<KpnSiteSign> findSignList(Long siteId) {
        LambdaQueryWrapper<KpnSiteSign> wrapper=new LambdaQueryWrapper<>();
        if (siteId!=null){
            wrapper.eq(KpnSiteSign::getSiteId,siteId);
        }
        return baseMapper.selectList(wrapper);
    }
}