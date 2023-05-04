package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.KpnSiteAppMapper;
import com.central.backend.service.IKpnSiteAppService;
import com.central.common.model.*;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 站点app更新配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Slf4j
@Service
public class KpnSiteAppServiceImpl extends SuperServiceImpl<KpnSiteAppMapper, KpnSiteApp> implements IKpnSiteAppService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<KpnSiteApp> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        Page<KpnSiteApp> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<KpnSiteApp> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnSiteApp>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public Result saveOrUpdateKpnSiteApp(KpnSiteApp kpnSiteApp, SysUser user){
        if(null!=kpnSiteApp.getId()&&0!=kpnSiteApp.getId()){
            kpnSiteApp.setUpdateBy(null!=user?user.getUsername():kpnSiteApp.getUpdateBy());
        }else {
            LambdaQueryWrapper<KpnSiteApp> wrapper = new LambdaQueryWrapper<>();
            if (null!=user){
                wrapper.eq(KpnSiteApp::getSiteId,user.getSiteId());
            }
            wrapper.eq(KpnSiteApp::getType,kpnSiteApp.getType());
            wrapper.eq(KpnSiteApp::getVersionNum,kpnSiteApp.getVersionNum());
            List<KpnSiteApp> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("版本已存在");
            }
            kpnSiteApp.setCreateBy(null!=user?user.getUsername():kpnSiteApp.getCreateBy());
            kpnSiteApp.setUpdateBy(null!=user?user.getUsername():kpnSiteApp.getCreateBy());
        }
        this.saveOrUpdate(kpnSiteApp);
        return Result.succeed("保存成功");
    }
}
