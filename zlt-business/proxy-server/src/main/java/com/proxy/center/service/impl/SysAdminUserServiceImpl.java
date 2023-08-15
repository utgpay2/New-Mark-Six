package com.proxy.center.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.GaBindCo;
import com.proxy.center.co.SysRoleUser;
import com.proxy.center.mapper.SysUserMapper;
import com.proxy.center.model.dto.SysAdminUserDto;
import com.proxy.center.model.dto.SysAdminUserEnabledDto;
import com.proxy.center.model.enums.RoleEnum;
import com.proxy.center.service.*;
import com.proxy.center.util.PasswordUtil;
import com.proxy.center.util.SecureToken;
import com.central.common.constant.CommonConstant;
import com.central.common.constant.RedisConstants;
import com.central.common.model.*;
import com.central.common.model.enums.UserRegTypeEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.GoogleAuthUtil;
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
import java.math.BigDecimal;
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
    private ISiteService iSiteService;
    private String passRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    RedisRepository redisRepository;

    @Override
    public void addRewardMb(SysUser sysUser, BigDecimal rewardMb) {
        this.lambdaUpdate().eq(SysUser::getId, sysUser.getId())
                .setSql("`m_balance` = `m_balance` + " + rewardMb)
                .update();
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, sysUser.getId());
        RedisRepository.delete(redisKey);
    }

    @Override
    public void addRewardTestMb(SysUser sysUser, BigDecimal rewardTestMb) {
        this.lambdaUpdate().eq(SysUser::getId, sysUser.getId())
                .setSql("`m_test_balance` = `m_test_balance` + " + rewardTestMb)
                .update();
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, sysUser.getId());
        RedisRepository.delete(redisKey);
    }

    @Override
    public PageResult<SysUser> findList(Map<String, Object> params, SysUser user){
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>();
        if(null!=user && user.getSiteId()!=null && user.getSiteId()!=0){//
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
        if(null!=user && user.getParentId()!=null && user.getParentId()!=0) {
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
     * 获取用户的角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return roleUserService.findRolesByUserId(userId);
    }

    @Override
    public LoginAppUser findByUsername(String username) {
        SysUser sysUser=baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username",username));
        return  sysUserService.getLoginAppUser(sysUser);
    }

    @Override
    public Result login(String username, String password,String verifyCode) {

        SysUser sysUser=baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username",username));
        if (sysUser == null || !sysUser.getEnabled()) {

            return Result.failed("用户名或密码错误");
        }
        if(!UserTypeEnum.BACKEND.name().equals(sysUser.getType())){

            return Result.failed("非管理员用户");
        }
        if(!sysUser.getEnabled()){

            return Result.failed("账户已禁用");
        }
        Pattern pattern = Pattern.compile(passRegex);//6-16位字母和数字组合
        //2.选择匹配对象
        Matcher usermatcher = pattern.matcher(password);
        if(!usermatcher.matches()){

            return Result.failed("密码必须是6-16位字母和数字组合");
        }

        if (StrUtil.isNotBlank(password)) {
            if (!passwordEncoder.matches(password, sysUser.getPassword())) {

                return Result.failed("密码错误");
            }
        }



        if (sysUser.getVerify() != null && sysUser.getVerify() == 1) {

            if (StrUtil.isBlank(verifyCode)) {
                return Result.failed("请输入验证码");
            }

            Integer code = Integer.parseInt(verifyCode);
            String secret = sysUser.getGaKey();


            if (StrUtil.isBlank(secret) || sysUser.getGaBind() == null || sysUser.getGaBind() != 1) {
                return Result.failed("请先绑定谷歌身份验证器");

            }
            boolean checkCode = GoogleAuthUtil.check_code(secret, code);
            if (!checkCode) {
                return Result.failed("谷歌身份验证失败");
            }
        }

        String token = SecureToken.generate();
        redisRepository.setExpire("token:"+token,0,12*60*60);
        return   Result.succeed(token, "succeed");
    }

    @Override
    public SysUser getMerchantAdministrator(String siteCode) {

        return baseMapper.getMerchantAdministrator(siteCode);
    }

    @Override
    public Result getProxyBySiteCode(String siteCode) {

       List<Map> users= baseMapper.getProxyBySiteCode(siteCode);
        return   Result.succeed(users);

    }

    /**
     * 给用户设置角色
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
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
                if(RoleEnum.MERCHANT_ADMIN.getStatus().equals(sysRole.getCode())||RoleEnum.SUPER_ADMIN.getStatus().equals(sysRole.getCode())){//系统管理员||超级管理员
                    b = true;
                }
            }
            if(b){
                if(adminUserVo.getSiteId()!=0){
                    return Result.failed("商户管理员不能授予系统管理员或者超级系统管理员权限");
                }
                //商户id
                user.setSiteId(0L);
                //商户编码
                user.setSiteCode("0");
                //商户名称
                user.setSiteName("0");
                user.setUsername(adminUserVo.getUsername());
            }else {
                Site site = iSiteService.getById(adminUserVo.getSiteId());
                if(null == site){
                    return Result.failed("请先添加商户");
                }else {
                    //商户id
                    user.setSiteId(site.getId());
                    //商户编码
                    user.setSiteCode(site.getCode());
                    //商户名称
                    user.setSiteName(site.getName());
                    user.setUsername(site.getCode()+"_"+adminUserVo.getUsername());
                }
            }
//            else {//商户管理员
//                user.setUsername(user.getSiteCode() + "_" +adminUserVo.getUsername());
//            }
            if(null!=sysUser){
                //上级id
                user.setParentId(sysUser.getId());
                //上级账号
                user.setParentName(sysUser.getUsername());

            }

            user.setNickname(adminUserVo.getUsername());
            user.setType(UserTypeEnum.BACKEND.name());
            user.setIsReg(UserRegTypeEnum.ADMIN_CREATE.getType());
            user.setEnabled(Boolean.TRUE);
            user.setUpdateBy(sysUser.getUsername());
            user.setCreateBy(sysUser.getUsername());
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
                if(RoleEnum.MERCHANT_ADMIN.getStatus().equals(sysRole.getCode())||RoleEnum.SUPER_ADMIN.getStatus().equals(sysRole.getCode())){//系统管理员||超级管理员
                    b = true;
                }
            }
            if(b){
                if(adminUserVo.getSiteId()!=0){
                    return Result.failed("商户管理员不能授予系统管理员或者超级系统管理员权限");
                }
                //商户id
                userInfo.setSiteId(0L);
                //商户编码
                userInfo.setSiteCode("0");
                //商户名称
                userInfo.setSiteName("0");
                user.setUsername(adminUserVo.getUsername());
            }else {
                Site site = iSiteService.getById(adminUserVo.getSiteId());
                if(null == site){
                    return Result.failed("请先添加商户");
                }else {
                    //商户id
                    userInfo.setSiteId(site.getId());
                    //商户编码
                    userInfo.setSiteCode(site.getCode());
                    //商户名称
                    userInfo.setSiteName(site.getName());
                    user.setUsername(site.getCode()+"_"+adminUserVo.getUsername());
                }
            }
            if(null!=sysUser){
                //上级id
                userInfo.setParentId(sysUser.getId());
                //上级账号
                userInfo.setParentName(sysUser.getUsername());

            }
            userInfo.setNickname(adminUserVo.getUsername());
            userInfo.setType(UserTypeEnum.BACKEND.name());
            userInfo.setIsReg(UserRegTypeEnum.ADMIN_CREATE.getType());
            userInfo.setEnabled(Boolean.TRUE);
            userInfo.setUpdateTime(new Date());
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