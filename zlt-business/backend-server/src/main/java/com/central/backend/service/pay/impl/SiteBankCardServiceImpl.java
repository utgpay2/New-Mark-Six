package com.central.backend.service.pay.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.pay.SiteBankCardMapper;
import com.central.backend.service.pay.ISiteBankCardService;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.pay.SiteBankCard;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 收款银行卡配置
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Slf4j
@Service
public class SiteBankCardServiceImpl extends SuperServiceImpl<SiteBankCardMapper, SiteBankCard> implements ISiteBankCardService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<SiteBankCard> findListPage(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        Page<SiteBankCard> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SiteBankCard> list  =  baseMapper.findList(page, params);
        return PageResult.<SiteBankCard>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public List<SiteBankCard> findList(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        return baseMapper.findList(params);
    }
    @Override
    public Result saveOrUpdateKpnSiteBankCard(SiteBankCard siteBankCard, SysUser user){
        if(null!= siteBankCard.getId()&&0!= siteBankCard.getId()){
            siteBankCard.setUpdateBy(null!=user?user.getUsername(): siteBankCard.getUpdateBy());
        }else {
            LambdaQueryWrapper<SiteBankCard> wrapper = new LambdaQueryWrapper<>();
            if (null!=user){
                wrapper.eq(SiteBankCard::getSiteId, user.getSiteId());
            }
            wrapper.eq(SiteBankCard::getCard, siteBankCard.getCard());
            List<SiteBankCard> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("卡号已存在");
            }
            siteBankCard.setCreateBy(null!=user?user.getUsername(): siteBankCard.getCreateBy());
            siteBankCard.setUpdateBy(null!=user?user.getUsername(): siteBankCard.getCreateBy());
        }
        this.saveOrUpdate(siteBankCard);
        return Result.succeed("保存成功");
    }
    @Override
    public Result deleteKpnSiteBankCard(@PathVariable Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
    }
}
