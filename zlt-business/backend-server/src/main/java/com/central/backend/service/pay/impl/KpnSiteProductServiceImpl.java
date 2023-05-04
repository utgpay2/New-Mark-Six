package com.central.backend.service.pay.impl;

import com.central.backend.mapper.pay.KpnSiteProductMapper;
import com.central.backend.service.pay.IKpnSiteProductService;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.pay.KpnSiteProduct;
import com.central.common.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * vip产品
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Slf4j
@Service
public class KpnSiteProductServiceImpl extends SuperServiceImpl<KpnSiteProductMapper, KpnSiteProduct> implements IKpnSiteProductService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<KpnSiteProduct> findListPage(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        Page<KpnSiteProduct> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<KpnSiteProduct> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnSiteProduct>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public List<KpnSiteProduct> findList(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        return baseMapper.findList(params);
    }
    @Override
    public Result saveOrUpdateKpnSiteProduct(KpnSiteProduct kpnSiteProduct, SysUser user){
        if(null!=kpnSiteProduct.getId()&&0!=kpnSiteProduct.getId()){
            kpnSiteProduct.setUpdateBy(null!=user?user.getUsername():kpnSiteProduct.getUpdateBy());
        }else {
            kpnSiteProduct.setCreateBy(null!=user?user.getUsername():kpnSiteProduct.getCreateBy());
            kpnSiteProduct.setUpdateBy(null!=user?user.getUsername():kpnSiteProduct.getCreateBy());
        }
        this.saveOrUpdate(kpnSiteProduct);
        return Result.succeed("保存成功");
    }
    @Override
    public Result deleteKpnSiteProduct(@PathVariable Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
    }
}
