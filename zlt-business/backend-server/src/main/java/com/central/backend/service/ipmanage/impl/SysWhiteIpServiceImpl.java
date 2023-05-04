package com.central.backend.service.ipmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.ipmanage.SysWhiteIpMapper;
import com.central.backend.service.ipmanage.ISysWhiteIpService;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.SysWhiteIp;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

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
 * @date 2023-02-03 15:07:56
 */
@Slf4j
@Service
public class SysWhiteIpServiceImpl extends SuperServiceImpl<SysWhiteIpMapper, SysWhiteIp> implements ISysWhiteIpService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<SysWhiteIp> findList(Map<String, Object> params){
        Page<SysWhiteIp> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SysWhiteIp> list  =  baseMapper.findList(page, params);
        return PageResult.<SysWhiteIp>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public Boolean ipcheck(String ip){
        Boolean b = false;
        LambdaQueryWrapper<SysWhiteIp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysWhiteIp::getIp,ip);
        List<SysWhiteIp> list  =  baseMapper.selectList(wrapper);
        if(null!=list&&list.size()>0){
            return true;
        }
        return b;
    }
    @Override
    public Result saveOrUpdateSysWhiteIp(SysWhiteIp sysWhiteIp, SysUser user){
        String ip = sysWhiteIp.getIp();
        if(null==ip || "".equals(ip)){
            return Result.failed("白名单IP不能为空");
        }
        //1.创建匹配模式
        Pattern pattern = Pattern.compile("(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])");//匹配一个或多个数字字符
        //2.选择匹配对象
        Matcher matcher = pattern.matcher(ip);
        if(!matcher.matches()){
            return Result.failed("白名单IP格式错误");
        }
        if(null!=sysWhiteIp.getId()&&0!=sysWhiteIp.getId()){
            sysWhiteIp.setUpdateBy(null!=user?user.getUsername():sysWhiteIp.getUpdateBy());
        }else {
            LambdaQueryWrapper<SysWhiteIp> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysWhiteIp::getIp,ip);
            List<SysWhiteIp> list  =  baseMapper.selectList(wrapper);
            if(null!=list&&list.size()>0){
                return Result.failed("黑名单IP已存在");
            }
            sysWhiteIp.setCreateBy(null!=user?user.getUsername():sysWhiteIp.getCreateBy());
            sysWhiteIp.setUpdateBy(null!=user?user.getUsername():sysWhiteIp.getCreateBy());
        }
        this.saveOrUpdate(sysWhiteIp);
        return Result.succeed("保存成功");
    }

    @Override
    public Result deleteSysWhiteIp(@PathVariable Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
    }
}
