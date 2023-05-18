package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.SiteServeMapper;
import com.central.backend.service.ISiteServeService;
import com.central.common.model.SiteServe;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 站点客服配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Slf4j
@Service
public class SiteServeServiceImpl extends SuperServiceImpl<SiteServeMapper, SiteServe> implements ISiteServeService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<SiteServe> findListPage(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        Page<SiteServe> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SiteServe> list  =  baseMapper.findList(page, params);
        return PageResult.<SiteServe>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public List<SiteServe> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        return baseMapper.findList(params);
    }
    @Override
    public Result saveOrUpdateKpnSiteServe(SiteServe siteServe, SysUser user){
        if(null!= siteServe.getId()&&0!= siteServe.getId()){
            siteServe.setUpdateBy(null!=user?user.getUsername(): siteServe.getUpdateBy());
        }else {
            LambdaQueryWrapper<SiteServe> wrapper = new LambdaQueryWrapper<>();
            if (null!=user){
                wrapper.eq(SiteServe::getSiteId,user.getSiteId());
            }
            wrapper.eq(SiteServe::getPlatform, siteServe.getPlatform());
            wrapper.eq(SiteServe::getServeAccount, siteServe.getServeAccount());
            List<SiteServe> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("平台客服账号已存在");
            }
            siteServe.setCreateBy(null!=user?user.getUsername(): siteServe.getCreateBy());
            siteServe.setUpdateBy(null!=user?user.getUsername(): siteServe.getCreateBy());
        }
        this.saveOrUpdate(siteServe);
        return Result.succeed("保存成功");
    }
}
