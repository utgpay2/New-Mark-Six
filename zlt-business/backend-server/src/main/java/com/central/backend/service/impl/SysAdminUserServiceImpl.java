package com.central.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.*;
import com.central.backend.mapper.SysUserMapper;
import com.central.backend.model.dto.SysAdminUserDto;
import com.central.backend.model.dto.SysAdminUserEnabledDto;
import com.central.backend.model.enums.RoleEnum;
import com.central.backend.service.*;
import com.central.backend.util.PasswordUtil;
import com.central.common.constant.CommonConstant;
import com.central.common.model.*;
import com.central.common.model.enums.UserRegTypeEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@Slf4j
@Service
@CacheConfig(cacheNames = {"sysUser"})
public class SysAdminUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements IAdminUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysRoleUserService roleUserService;
    @Resource
    private IKpnSiteService iKpnSiteService;
    private String passRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    @Override
    public PageResult<SysUser> findList(Map<String, Object> params, SysUser user){
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>();
        if(null!=user && user.getSiteId()==null && user.getSiteId()!=0){//
            wrapper.eq(SysUser::getSiteId, user.getSiteId());
        }
        String username = MapUtils.getString(params, "username");
        if(null!=username&&!"".equals(username)){
            wrapper.eq(SysUser::getUsername, username);
        }
        String enabled = MapUtils.getString(params, "enabled");
        if(null!=enabled&&!"".equals(enabled)) {
            if("1".equals(enabled)) {
                wrapper.eq(SysUser::getEnabled, true);
            }else {
                wrapper.eq(SysUser::getEnabled, false);
            }
        }
        wrapper.eq(SysUser::getIsDel, false);
        //账号类型：APP：前端app用户，BACKEND：后端管理用户
        wrapper.eq(SysUser::getType, UserTypeEnum.BACKEND.name());
        if(null!=user && user.getParentId()==null && user.getParentId()!=0) {
            wrapper.eq(SysUser::getParentId, user.getId());
        }
        wrapper.orderByDesc(SysUser::getUpdateTime);

        Page<SysUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        Page<SysUser> list = baseMapper.selectPage(page, wrapper);
        for (SysUser sysUser:list.getRecords()){
            List<SysRole> sysRoles = roleUserService.findRolesByUserId(sysUser.getId());
            // 设置角色
            sysUser.setRoles(sysRoles);
        }

        return PageResult.<SysUser>builder().data(list.getRecords()).count(page.getTotal()).build();
    }
    @Override
    public SysUser selectById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 管理后台，给用户重置密码
     * @param id
     * @return
     */
    @Transactional
    @Override
    public String resetUpdatePassword(Long id) {
        SysUser sysUser = baseMapper.selectById(id);
//        SysUser user = new SysUser();
//        user.setId(id);
        //随机生成
        String password = PasswordUtil.getRandomPwd();
        sysUser.setPassword(passwordEncoder.encode(password));
        baseMapper.updateById(sysUser);
        return password;
    }
    /**
     * 管理员自己修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Transactional
    @Override
    public Result updatePassword(Long id, String oldPassword, String newPassword) {
        //1.创建匹配模式
        Pattern pattern = Pattern.compile(passRegex);//6-16位字母和数字组合
        //2.选择匹配对象
        Matcher usermatcher = pattern.matcher(newPassword);
        if(!usermatcher.matches()){
            return Result.failed("密码必须是6-16位字母和数字组合");
        }
        SysUser sysUser = baseMapper.selectById(id);
        if (StrUtil.isNotBlank(oldPassword)) {
            if (!passwordEncoder.matches(oldPassword, sysUser.getPassword())) {
                return Result.failed("旧密码错误");
            }
        }
        if (StrUtil.isBlank(newPassword)) {
            newPassword = CommonConstant.DEF_USER_PASSWORD;
        }
        sysUser.setId(id);
        sysUser.setPassword(passwordEncoder.encode(newPassword));
        baseMapper.updateById(sysUser);
        return Result.succeed("修改成功");
    }

    /**
     * 修改用户状态
     * @param enabledDto
     * @return
     */
    @Transactional
    @Override
    public Result updateEnabled(SysAdminUserEnabledDto enabledDto) {
        SysUser sysUser = baseMapper.selectById(enabledDto.getId());
        sysUser.setEnabled(enabledDto.getEnabled());
        baseMapper.updateById(sysUser);
        return Result.succeed("修改成功");
    }

    /**
     * 给用户设置角色
     */
    @Transactional(rollbackFor = Exception.class)
    public void setRoleToUser(Long id, Set<Long> roleIds) {
        SysUser sysUser = baseMapper.selectById(id);
        if (sysUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        roleUserService.deleteUserRole(id, null);
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysRoleUser> roleUsers = new ArrayList<>(roleIds.size());
            roleIds.forEach(roleId -> roleUsers.add(new SysRoleUser(id, roleId)));
            roleUserService.saveBatch(roleUsers);
        }
    }


    /**
     * 添加管理员
     * @param adminUserVo,sysUser
     * @return
     * @Author: 一休
     * @Date: 2023/3/9
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveOrUpdateAdminInfo(SysAdminUserDto adminUserVo, SysUser sysUser) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(adminUserVo, user);
        boolean insert =false;
        //新增
        if (adminUserVo.getId() == null || adminUserVo.getId()==0) {
            //1.创建匹配模式
            Pattern pattern = Pattern.compile(passRegex);//6-16位字母和数字组合
            //2.选择匹配对象
            Matcher usermatcher = pattern.matcher(user.getUsername());
            if(!usermatcher.matches()){
                return Result.failed("用户账号必须是6-16位字母和数字组合");
            }
            LambdaQueryWrapper<SysUser> wrapper=new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(user.getUsername())){
                wrapper.eq(SysUser::getUsername, user.getUsername());
            }
            wrapper.eq(SysUser::getType, UserTypeEnum.BACKEND.name());
            Integer integer = baseMapper.selectCount(wrapper);
            if (integer>0){
                return Result.failed("管理员账号已存在");
            }

            if (StringUtils.isBlank(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(CommonConstant.DEF_USER_PASSWORD));
            } else {
                //2.选择匹配对象
                Matcher matcher = pattern.matcher(user.getPassword());
                if(!matcher.matches()){
                    return Result.failed("密码必须是6-16位字母和数字组合");
                }
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            Set<Long> roleIds = adminUserVo.getRoleIds();
            Iterator it = roleIds.iterator();
            boolean b = false;
            while (it.hasNext()) {
                SysRole sysRole = sysRoleService.getById((Long) it.next());
                if(RoleEnum.MERCHANT_ADMIN.getStatus().equals(sysRole.getCode())){//商户管理员
                    b = true;
                }
            }
            if(b){//商户管理员
                user.setRoleSites(JSONObject.toJSONString(adminUserVo.getSiteIds()));
                //站点id
                user.setSiteId(0L);
                //站点编码
                user.setSiteCode("0");
                //站点名称
                user.setSiteName("0");
            }else {
                if(null==adminUserVo.getSiteIds()||adminUserVo.getSiteIds().size()==0){
                    return Result.failed("站点权限不能为空");
                }else if(adminUserVo.getSiteIds().size()>1){
                    return Result.failed("站点管理员只能唯一一个站点权限");
                }else {//站点管理员
                    Set<Long> siteIds = adminUserVo.getSiteIds();
                    Iterator s = siteIds.iterator();
                    while (s.hasNext()) {
                        KpnSite kpnSite = iKpnSiteService.getById((Long)s.next());
                        //站点id
                        user.setSiteId(kpnSite.getId());
                        //站点编码
                        user.setSiteCode(kpnSite.getCode());
                        //站点名称
                        user.setSiteName(kpnSite.getName());
                    }
                }
            }
            if(null!=sysUser){
                //上级id
                user.setParentId(sysUser.getId());
                //上级账号
                user.setParentName(sysUser.getUsername());
                user.setUpdateBy(sysUser.getUsername());
                user.setCreateBy(sysUser.getUsername());
            }
            user.setNickname(user.getUsername());
            user.setType(UserTypeEnum.BACKEND.name());
            user.setIsReg(UserRegTypeEnum.ADMIN_CREATE.getType());
            user.setEnabled(Boolean.TRUE);

            user.setUpdateTime(new Date());

            insert = super.save(user);
            LambdaQueryWrapper<SysUser> wrapper2 =new LambdaQueryWrapper<>();
            wrapper2.eq(SysUser::getUsername, user.getUsername());
            wrapper2.eq(SysUser::getType, UserTypeEnum.BACKEND.name());
            SysUser admin = baseMapper.selectOne(wrapper2);
            this.setRoleToUser(admin.getId(),adminUserVo.getRoleIds());
        }else {
            SysUser userInfo = baseMapper.selectById(user.getId());
            if (userInfo == null) {
                return Result.failed("管理员不存在");
            }
            Set<Long> roleIds = adminUserVo.getRoleIds();
            Iterator it = roleIds.iterator();
            boolean b = false;
            while (it.hasNext()) {
                SysRole sysRole = sysRoleService.getById((Long) it.next());
                if(RoleEnum.MERCHANT_ADMIN.getStatus().equals(sysRole.getCode())){//商户管理员
                    b = true;
                }
            }
            if(b){//商户管理员
                userInfo.setRoleSites(JSONObject.toJSONString(adminUserVo.getSiteIds()));
            }else {
                if(null==adminUserVo.getSiteIds()||adminUserVo.getSiteIds().size()==0){
                    return Result.failed("站点权限不能为空");
                }else if(adminUserVo.getSiteIds().size()>1){
                    return Result.failed("站点管理员只能唯一一个站点权限");
                }else {//站点管理员
                    Set<Long> siteIds = adminUserVo.getSiteIds();
                    Iterator s = siteIds.iterator();
                    while (s.hasNext()) {
                        KpnSite kpnSite = iKpnSiteService.getById((Long)s.next());
                        //站点id
                        userInfo.setSiteId(kpnSite.getId());
                        //站点编码
                        userInfo.setSiteCode(kpnSite.getCode());
                        //站点名称
                        userInfo.setSiteName(kpnSite.getName());
                    }
                }
            }

            //userInfo.setMobile(user.getMobile());
            userInfo.setUpdateBy(null!=sysUser?sysUser.getUpdateBy():"");
            //用户名
            userInfo.setUsername(adminUserVo.getUsername());
            //状态：0/false.禁用，1/true.启用")
            userInfo.setEnabled(adminUserVo.getEnabled());
            //备注
            userInfo.setRemark(adminUserVo.getRemark());
            int i = baseMapper.updateById(userInfo);
            insert = i > 0 ? true : false;
            if(i > 0) {
                this.setRoleToUser(userInfo.getId(), adminUserVo.getRoleIds());
                insert = true;
            }else {
                insert = false;
            }
        }
        if (insert) {
            return Result.succeed(user, "操作成功");
        }
        return Result.failed("操作失败");
    }
    @Override
    public Result updateVerify(Long id) {
        SysUser appUser = baseMapper.selectById(id);
        if (appUser == null) {
            return Result.failed("用户不存在");
        }
        if (appUser.getVerify() != null && appUser.getVerify() == 1) {
            appUser.setVerify(2);
        } else {
            appUser.setVerify(1);
        }
        appUser.setUpdateTime(new Date());
        int i = baseMapper.updateById(appUser);
        log.info("修改用户：{}", appUser);

        return i > 0 ? Result.succeed(appUser, "更新成功") : Result.failed("更新失败");
    }
    @Override
    public Result updateGaBind(GaBindCo params) {
        Long id = params.getId();
        Integer gaBind = params.getGaBind();

        SysUser appUser = baseMapper.selectById(id);
        if (appUser == null) {
            return Result.failed("用户不存在");
        }
        appUser.setGaBind(gaBind);
        appUser.setUpdateTime(new Date());

        int i = baseMapper.updateById(appUser);
        log.info("修改用户：{}", appUser);

        return i > 0 ? Result.succeed(appUser, "更新成功") : Result.failed("更新失败");
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delUser(Long id) {
        roleUserService.deleteUserRole(id, null);
        return baseMapper.deleteById(id) > 0;
    }
}