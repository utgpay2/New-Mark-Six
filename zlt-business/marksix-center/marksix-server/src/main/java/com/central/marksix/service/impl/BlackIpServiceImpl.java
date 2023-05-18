package com.central.marksix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.model.ipmanage.BlackIp;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.BlackIpMapper;
import com.central.marksix.service.IBlackIpService;
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
public class BlackIpServiceImpl extends SuperServiceImpl<BlackIpMapper, BlackIp> implements IBlackIpService {
    @Override
    public Boolean ipcheck(String ip, String siteId){
        Boolean b = false;
        LambdaQueryWrapper<BlackIp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlackIp::getIpSection,ip);
        if(null!=siteId && !"".equals(siteId)){
            wrapper.eq(BlackIp::getSiteId,siteId);
        }
        List<BlackIp> list  =  baseMapper.selectList(wrapper);
        if(null!=list&&list.size()>0){
            return true;
        }
        return b;
    }
}
