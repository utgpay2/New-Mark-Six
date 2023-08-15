package com.proxy.center.service.impl;


import com.proxy.center.mapper.ThirdPartyMapper;
import com.proxy.center.service.IThirdPartyService;
import com.central.common.model.ThirdParty;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThirdPartyServiceImpl extends SuperServiceImpl<ThirdPartyMapper, ThirdParty> implements IThirdPartyService {

}
