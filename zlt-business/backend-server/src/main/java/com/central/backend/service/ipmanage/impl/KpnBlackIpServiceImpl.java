package com.central.backend.service.ipmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.ipmanage.KpnBlackIpMapper;
import com.central.backend.model.vo.KpnBlackIpVO;
import com.central.backend.service.ipmanage.IKpnBlackIpService;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.KpnBlackIp;
import com.central.common.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Slf4j
@Service
public class KpnBlackIpServiceImpl extends SuperServiceImpl<KpnBlackIpMapper, KpnBlackIp> implements IKpnBlackIpService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<KpnBlackIpVO> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){//
            params.put("siteId",user.getSiteId());
        }
        Page<KpnBlackIpVO> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<KpnBlackIpVO> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnBlackIpVO>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public Result saveOrUpdateKpnBlackIp(KpnBlackIp kpnBlackIp, SysUser user){
        String ip = kpnBlackIp.getIpSection();
        if(null==ip || "".equals(ip)){
            return Result.failed("黑名单IP不能为空");
        }
        //1.创建匹配模式
        Pattern pattern = Pattern.compile("(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])");//匹配一个或多个数字字符
        //2.选择匹配对象
        Matcher matcher = pattern.matcher(ip);
        if(!matcher.matches()){
            return Result.failed("黑名单IP格式错误");
        }
        if(null!=kpnBlackIp.getId()&&0!=kpnBlackIp.getId()){
            kpnBlackIp.setUpdateBy(null!=user?user.getUsername():kpnBlackIp.getUpdateBy());
        }else {
            LambdaQueryWrapper<KpnBlackIp> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(KpnBlackIp::getIpSection,ip);
            if(null!=user){
                wrapper.eq(KpnBlackIp::getSiteId,user.getSiteId());
            }
            List<KpnBlackIp> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("黑名单IP已存在");
            }
            kpnBlackIp.setCreateBy(null!=user?user.getUsername():kpnBlackIp.getCreateBy());
            kpnBlackIp.setUpdateBy(null!=user?user.getUsername():kpnBlackIp.getCreateBy());
        }
        this.saveOrUpdate(kpnBlackIp);
        return Result.succeed("保存成功");
    }
    @Override
    public Result deleteKpnBlackIp(@PathVariable Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
    }
    @Override
    public Boolean ipcheck(String ip, SysUser user){
        Boolean b = false;
        LambdaQueryWrapper<KpnBlackIp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KpnBlackIp::getIpSection,ip);
        if(null!=user){
            wrapper.eq(KpnBlackIp::getSiteId,user.getSiteId());
        }
        List<KpnBlackIp> list  =  baseMapper.selectList(wrapper);
        if(null!=list&&list.size()>0){
            return true;
        }
        return b;
    }
}
