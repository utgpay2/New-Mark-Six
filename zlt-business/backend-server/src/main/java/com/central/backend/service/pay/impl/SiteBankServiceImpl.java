package com.central.backend.service.pay.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.pay.KpnSiteBankMapper;
import com.central.backend.service.pay.ISiteBankCardService;
import com.central.backend.service.pay.ISiteBankService;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.pay.SiteBank;
import com.central.common.model.pay.SiteBankCard;
import com.central.common.service.impl.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 收款银行渠道
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Slf4j
@Service
public class SiteBankServiceImpl extends SuperServiceImpl<KpnSiteBankMapper, SiteBank> implements ISiteBankService {
    @Autowired
    private ISiteBankCardService iSiteBankCardService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<SiteBank> findListPage(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        Page<SiteBank> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SiteBank> list  =  baseMapper.findList(page, params);
        return PageResult.<SiteBank>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public List<SiteBank> findList(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        return baseMapper.findList(params);
    }
    @Override
    public Result saveOrUpdateKpnSiteBank(SiteBank siteBank, SysUser user){
        if(null!= siteBank.getId()&&0!= siteBank.getId()){
            siteBank.setUpdateBy(null!=user?user.getUsername(): siteBank.getUpdateBy());
        }else {
            LambdaQueryWrapper<SiteBank> wrapper = new LambdaQueryWrapper<>();
            if (null!=user){
                wrapper.eq(SiteBank::getSiteId,user.getSiteId());
            }
            wrapper.eq(SiteBank::getName, siteBank.getName());
            List<SiteBank> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("银行名称已存在");
            }
            siteBank.setCreateBy(null!=user?user.getUsername(): siteBank.getCreateBy());
            siteBank.setUpdateBy(null!=user?user.getUsername(): siteBank.getCreateBy());
        }
        this.saveOrUpdate(siteBank);
        return Result.succeed("保存成功");
    }
    @Override
    public Result deleteKpnSiteBank(@PathVariable Long id){
        Map<String, Object> params = new HashMap<>();
        params.put("bankId",id);
        List<SiteBankCard> list = iSiteBankCardService.findList(params,null);
        if(null!=list && list.size()>0) {
            return Result.failed("当前渠道有关联卡号，不可删除");
        }
        this.removeById(id);
        return Result.succeed("删除成功");
    }
}
