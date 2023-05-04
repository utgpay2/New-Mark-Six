package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.backend.mapper.KpnSiteTopicComposingMapper;
import com.central.backend.service.IKpnSiteTopicComposingService;
import com.central.common.model.*;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class KpnSiteTopicComposingServiceImpl extends SuperServiceImpl<KpnSiteTopicComposingMapper, KpnSiteTopicComposing> implements IKpnSiteTopicComposingService {


    @Override
    public List<KpnSiteTopicComposing> findTopicComposingList() {
        return baseMapper.selectList(
                new QueryWrapper<KpnSiteTopicComposing>().orderByAsc("id")
        );
    }
}