package com.central.porn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.model.ipmanage.KpnBlackIp;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.mapper.KpnBlackIpMapper;
import com.central.porn.service.IKpnBlackIpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Slf4j
@Service
public class KpnBlackIpServiceImpl extends SuperServiceImpl<KpnBlackIpMapper, KpnBlackIp> implements IKpnBlackIpService {
    @Override
    public Boolean ipcheck(String ip, String siteId){
        Boolean b = false;
        LambdaQueryWrapper<KpnBlackIp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KpnBlackIp::getIpSection,ip);
        if(null!=siteId && !"".equals(siteId)){
            wrapper.eq(KpnBlackIp::getSiteId,siteId);
        }
        List<KpnBlackIp> list  =  baseMapper.selectList(wrapper);
        if(null!=list&&list.size()>0){
            return true;
        }
        return b;
    }
}
