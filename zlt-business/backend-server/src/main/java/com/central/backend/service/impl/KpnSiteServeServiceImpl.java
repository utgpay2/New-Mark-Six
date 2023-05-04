package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.KpnSiteServeMapper;
import com.central.backend.service.IKpnSiteServeService;
import com.central.common.model.KpnSiteServe;
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
public class KpnSiteServeServiceImpl extends SuperServiceImpl<KpnSiteServeMapper, KpnSiteServe> implements IKpnSiteServeService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<KpnSiteServe> findListPage(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        Page<KpnSiteServe> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<KpnSiteServe> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnSiteServe>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public List<KpnSiteServe> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        return baseMapper.findList(params);
    }
    @Override
    public Result saveOrUpdateKpnSiteServe(KpnSiteServe kpnSiteServe, SysUser user){
        if(null!=kpnSiteServe.getId()&&0!=kpnSiteServe.getId()){
            kpnSiteServe.setUpdateBy(null!=user?user.getUsername():kpnSiteServe.getUpdateBy());
        }else {
            LambdaQueryWrapper<KpnSiteServe> wrapper = new LambdaQueryWrapper<>();
            if (null!=user){
                wrapper.eq(KpnSiteServe::getSiteId,user.getSiteId());
            }
            wrapper.eq(KpnSiteServe::getPlatform,kpnSiteServe.getPlatform());
            wrapper.eq(KpnSiteServe::getServeAccount,kpnSiteServe.getServeAccount());
            List<KpnSiteServe> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("平台客服账号已存在");
            }
            kpnSiteServe.setCreateBy(null!=user?user.getUsername():kpnSiteServe.getCreateBy());
            kpnSiteServe.setUpdateBy(null!=user?user.getUsername():kpnSiteServe.getCreateBy());
        }
        this.saveOrUpdate(kpnSiteServe);
        return Result.succeed("保存成功");
    }
}
