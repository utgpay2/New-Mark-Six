package com.central.backend.service;


import com.central.backend.mapper.ThirdPartyMapper;

import com.central.common.model.ThirdParty;

import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThirdPartyServiceImpl extends SuperServiceImpl<ThirdPartyMapper, ThirdParty> implements IThirdPartyService{

}
