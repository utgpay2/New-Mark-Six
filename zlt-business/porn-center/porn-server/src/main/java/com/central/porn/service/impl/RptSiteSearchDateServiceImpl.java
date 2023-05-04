package com.central.porn.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.model.RptSiteSearchDate;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.vo.KpnSiteSearchVo;
import com.central.porn.mapper.RptSiteSearchDateMapper;
import com.central.porn.service.IRptSiteSearchDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RptSiteSearchDateServiceImpl extends SuperServiceImpl<RptSiteSearchDateMapper, RptSiteSearchDate> implements IRptSiteSearchDateService {

    @Async
    @Override
    public void saveRptSiteSearchDateNumber(Long sid, String keywords) {
        String date = DateUtil.formatDate(new Date());

        baseMapper.saveRptSiteSearchDateNumber(sid, date, keywords);
    }

    @Override
    public List<KpnSiteSearchVo> getSiteSearchWeek(Long sid) {
        String redisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_SEARCH_WEEK_KEY, sid);
        List<KpnSiteSearchVo> siteSearchMonthVos = (ArrayList<KpnSiteSearchVo>) RedisRepository.get(redisKey);
        if (CollectionUtil.isEmpty(siteSearchMonthVos)) {
            String endDate = DateUtil.formatDate(new Date());
            String startDate = DateUtil.formatDate(DateUtil.offsetDay(new Date(), -7));
            log.info("startDate:{}, endDate:{}", startDate, endDate);

            List<KpnSiteSearchVo> kpnSiteSearchMonthVos = this.baseMapper.getSiteSearchMonth(sid, startDate, endDate);

            if (CollectionUtil.isNotEmpty(kpnSiteSearchMonthVos)) {
                RedisRepository.setExpire(redisKey, kpnSiteSearchMonthVos, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return siteSearchMonthVos;
    }
}
