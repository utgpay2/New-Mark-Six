package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.SiteSignMapper;
import com.central.backend.service.ISiteSignService;
import com.central.common.model.SiteSign;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class SiteSignServiceImpl extends SuperServiceImpl<SiteSignMapper, SiteSign> implements ISiteSignService {

    @Override
    public List<SiteSign> findSignList(Long siteId) {
        LambdaQueryWrapper<SiteSign> wrapper=new LambdaQueryWrapper<>();
        if (siteId!=null){
            wrapper.eq(SiteSign::getSiteId,siteId);
        }
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Boolean saveOrUpdateSign(List<SiteSign> list) {
        return super.saveOrUpdateBatch(list);
    }
}