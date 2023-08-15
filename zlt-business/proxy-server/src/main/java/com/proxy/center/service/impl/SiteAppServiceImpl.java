package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.mapper.SiteAppMapper;
import com.proxy.center.service.ISiteAppService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SiteApp;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 商户app更新配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Slf4j
@Service
public class SiteAppServiceImpl extends SuperServiceImpl<SiteAppMapper, SiteApp> implements ISiteAppService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<SiteApp> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        Page<SiteApp> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SiteApp> list  =  baseMapper.findList(page, params);
        return PageResult.<SiteApp>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public Result saveOrUpdateKpnSiteApp(SiteApp siteApp, SysUser user){
        if(null!= siteApp.getId()&&0!= siteApp.getId()){
            siteApp.setUpdateBy(null!=user?user.getUsername(): siteApp.getUpdateBy());
        }else {
            LambdaQueryWrapper<SiteApp> wrapper = new LambdaQueryWrapper<>();
            if (null!=user){
                wrapper.eq(SiteApp::getSiteId,user.getSiteId());
            }
            wrapper.eq(SiteApp::getType, siteApp.getType());
            wrapper.eq(SiteApp::getVersionNum, siteApp.getVersionNum());
            List<SiteApp> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("版本已存在");
            }
            siteApp.setCreateBy(null!=user?user.getUsername(): siteApp.getCreateBy());
            siteApp.setUpdateBy(null!=user?user.getUsername(): siteApp.getCreateBy());
        }
        this.saveOrUpdate(siteApp);
        return Result.succeed("保存成功");
    }
}
