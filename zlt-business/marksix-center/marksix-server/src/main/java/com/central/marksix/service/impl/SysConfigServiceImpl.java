package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.Line;
import com.central.common.model.Site;
import com.central.common.model.SysConfig;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.LineMapper;
import com.central.marksix.mapper.SysConfigMapper;
import com.central.marksix.service.ILineService;
import com.central.marksix.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class SysConfigServiceImpl extends SuperServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    @Override
    public String getUrl(Integer platformType) {


        String urlKey = StrUtil.format(MarksixConstants.RedisKey.MKS_URL_INFO_KEY, platformType);
        String url;
        if (!RedisRepository.exists(urlKey)) {
            SysConfig sysConfig= this.getOne(new QueryWrapper<SysConfig>().
                    eq("type",platformType).orderByDesc("access_weight","id").last(" limit 1"));
             url=sysConfig.getUrl() + (StringUtils.hasText(sysConfig.getParam())?  "?"+sysConfig.getParam():"");
             RedisRepository.setExpire(urlKey, url, MarksixConstants.RedisKey.EXPIRE_TIME_30_DAYS);
        }else {
             url =  RedisRepository.get(urlKey).toString();
        }

        return url;
    }
}
