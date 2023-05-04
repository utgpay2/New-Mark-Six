package com.central.porn.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.RptSiteSearchTotal;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.vo.KpnSiteSearchVo;
import com.central.porn.mapper.RptSiteSearchTotalMapper;
import com.central.porn.service.IRptSiteSearchTotalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RptSiteSearchTotalServiceImpl extends SuperServiceImpl<RptSiteSearchTotalMapper, RptSiteSearchTotal> implements IRptSiteSearchTotalService {

    @Async
    @Override
    public void saveRptSiteSearchTotalNumber(Long sid, String keywords) {
        baseMapper.saveRptSiteSearchTotalNumber(sid, keywords);
    }

    @Override
    public List<KpnSiteSearchVo> getSiteSearchTotal(Long sid) {
        String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_SEARCH_TOTAL_KEY, sid);
        List<KpnSiteSearchVo> siteSearchTotalVos = (ArrayList) RedisRepository.get(redisKey);
        if (CollectionUtil.isEmpty(siteSearchTotalVos)) {
            siteSearchTotalVos = this.baseMapper.getSiteSearchTotal(sid);
            if (CollectionUtil.isNotEmpty(siteSearchTotalVos)) {
                RedisRepository.setExpire(redisKey, siteSearchTotalVos, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return siteSearchTotalVos;
    }
}
