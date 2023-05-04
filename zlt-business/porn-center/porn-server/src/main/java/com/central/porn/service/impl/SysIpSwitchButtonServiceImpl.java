package com.central.porn.service.impl;

import com.central.common.model.ipmanage.SysIpSwitchButton;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.SysIpSwitchButtonMapper;
import com.central.porn.service.ISysSysIpSwitchButtonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:07:56
 */
@Slf4j
@Service
public class SysIpSwitchButtonServiceImpl extends SuperServiceImpl<SysIpSwitchButtonMapper, SysIpSwitchButton> implements ISysSysIpSwitchButtonService {
    /**
     * 列表
     * @return
     */
    @Override
    public List<SysIpSwitchButton> findList(){
        return baseMapper.findList();
    }
}
