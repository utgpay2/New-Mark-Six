package com.central.marksix.service.impl;

import com.central.common.model.KpnSiteUserChannel;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.KpnSiteUserChannelMapper;
import com.central.marksix.service.IKpnSiteUserChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KpnSiteUserChannelServiceImpl extends SuperServiceImpl<KpnSiteUserChannelMapper, KpnSiteUserChannel> implements IKpnSiteUserChannelService {
}
