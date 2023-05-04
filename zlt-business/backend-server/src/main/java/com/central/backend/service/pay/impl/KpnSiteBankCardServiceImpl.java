package com.central.backend.service.pay.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.pay.KpnSiteBankCardMapper;
import com.central.backend.service.pay.IKpnSiteBankCardService;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.pay.KpnSiteBankCard;
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
public class KpnSiteBankCardServiceImpl extends SuperServiceImpl<KpnSiteBankCardMapper, KpnSiteBankCard> implements IKpnSiteBankCardService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<KpnSiteBankCard> findListPage(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        Page<KpnSiteBankCard> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<KpnSiteBankCard> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnSiteBankCard>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public List<KpnSiteBankCard> findList(Map<String, Object> params, SysUser user){
        if(null==user || user.getSiteId()==null || user.getSiteId()==0){//
            params.put("siteId","");
        }else {
            params.put("siteId",user.getSiteId());
        }
        return baseMapper.findList(params);
    }
    @Override
    public Result saveOrUpdateKpnSiteBankCard(KpnSiteBankCard kpnSiteBankCard, SysUser user){
        if(null!=kpnSiteBankCard.getId()&&0!=kpnSiteBankCard.getId()){
            kpnSiteBankCard.setUpdateBy(null!=user?user.getUsername():kpnSiteBankCard.getUpdateBy());
        }else {
            LambdaQueryWrapper<KpnSiteBankCard> wrapper = new LambdaQueryWrapper<>();
            if (null!=user){
                wrapper.eq(KpnSiteBankCard::getSiteId, user.getSiteId());
            }
            wrapper.eq(KpnSiteBankCard::getCard,kpnSiteBankCard.getCard());
            List<KpnSiteBankCard> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("卡号已存在");
            }
            kpnSiteBankCard.setCreateBy(null!=user?user.getUsername():kpnSiteBankCard.getCreateBy());
            kpnSiteBankCard.setUpdateBy(null!=user?user.getUsername():kpnSiteBankCard.getCreateBy());
        }
        this.saveOrUpdate(kpnSiteBankCard);
        return Result.succeed("保存成功");
    }
    @Override
    public Result deleteKpnSiteBankCard(@PathVariable Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
    }
}
