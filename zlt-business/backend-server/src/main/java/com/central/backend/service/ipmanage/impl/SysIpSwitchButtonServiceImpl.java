package com.central.backend.service.ipmanage.impl;

import com.central.backend.mapper.ipmanage.SysIpSwitchButtonMapper;
import com.central.backend.service.ipmanage.ISysSysIpSwitchButtonService;
import com.central.common.model.ipmanage.SysIpSwitchButton;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


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
