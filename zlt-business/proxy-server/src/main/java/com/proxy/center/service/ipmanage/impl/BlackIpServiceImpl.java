package com.proxy.center.service.ipmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.mapper.ipmanage.BlackIpMapper;
import com.proxy.center.model.vo.BlackIpVo;
import com.proxy.center.service.ipmanage.IBlackIpService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.BlackIp;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Slf4j
@Service
public class BlackIpServiceImpl extends SuperServiceImpl<BlackIpMapper, BlackIp> implements IBlackIpService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<BlackIpVo> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){//
            params.put("siteId",user.getSiteId());
        }
        Page<BlackIpVo> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<BlackIpVo> list  =  baseMapper.findList(page, params);
        return PageResult.<BlackIpVo>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public Result saveOrUpdateBlackIp(BlackIp blackIp, SysUser user){
        String ip = blackIp.getIpSection();
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
        if(null!= blackIp.getId()&&0!= blackIp.getId()){
            blackIp.setUpdateBy(null!=user?user.getUsername(): blackIp.getUpdateBy());
        }else {
            LambdaQueryWrapper<BlackIp> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(BlackIp::getIpSection,ip);
            if(null!=user){
                wrapper.eq(BlackIp::getSiteId,user.getSiteId());
            }
            List<BlackIp> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("黑名单IP已存在");
            }
            blackIp.setCreateBy(null!=user?user.getUsername(): blackIp.getCreateBy());
            blackIp.setUpdateBy(null!=user?user.getUsername(): blackIp.getCreateBy());
        }
        this.saveOrUpdate(blackIp);
        return Result.succeed("保存成功");
    }
    @Override
    public Result deleteBlackIp(@PathVariable Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
    }
    @Override
    public Boolean ipcheck(String ip, SysUser user){
        Boolean b = false;
        LambdaQueryWrapper<BlackIp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlackIp::getIpSection,ip);
        if(null!=user){
            wrapper.eq(BlackIp::getSiteId,user.getSiteId());
        }
        List<BlackIp> list  =  baseMapper.selectList(wrapper);
        if(null!=list&&list.size()>0){
            return true;
        }
        return b;
    }
}
