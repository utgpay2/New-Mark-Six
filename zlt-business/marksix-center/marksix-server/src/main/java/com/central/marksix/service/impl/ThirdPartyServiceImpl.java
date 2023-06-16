package com.central.marksix.service.impl;



import com.central.common.model.ThirdParty;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.ThirdPartyMapper;
import com.central.marksix.service.IThirdPartyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThirdPartyServiceImpl extends SuperServiceImpl<ThirdPartyMapper, ThirdParty> implements IThirdPartyService {

}
