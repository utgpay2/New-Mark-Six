package com.central.backend.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.*;
import com.central.backend.mapper.SysRoleMenuMapper;
import com.central.backend.mapper.SysUserMapper;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IRptSiteSummaryService;
import com.central.backend.service.ISysRoleUserService;
import com.central.backend.service.ISysUserService;
import com.central.backend.util.DateUtil;
import com.central.backend.util.PasswordUtil;
import com.central.backend.vo.SysUserInfoMoneyVo;
import com.central.backend.vo.UserExtensionListInfoVo;
import com.central.backend.vo.UserListInfoVo;
import com.central.backend.vo.UserVipExpireVo;
import com.central.common.constant.CommonConstant;
import com.central.common.constant.PornConstants;
import com.central.common.lock.DistributedLock;
import com.central.common.model.*;
import com.central.common.model.enums.UserRegTypeEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
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
import java.util.stream.Collectors;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@Slf4j
@Service
@CacheConfig(cacheNames = {"sysUser"})
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final static String LOCK_KEY_USERNAME = "username:";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private ISysRoleUserService roleUserService;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private IAsyncService asyncService;

    @Autowired
    private DistributedLock lock;


    @Autowired
    private IRptSiteSummaryService summaryService;



    @Override
    public PageResult<SysUser> findList(Map<String, Object> params, SysUser user){
        QueryWrapper wrapper = new QueryWrapper<SysUser>();
        if(user.getSiteId()==null || user.getSiteId()==0){//
        }else {
            wrapper.eq("siteId", user.getSiteId());
        }
        String username = MapUtils.getString(params, "limit");
        if(null!=username&&!"".equals(username)){
            wrapper.eq("username", username);
        }
        String enabled = MapUtils.getString(params, "enabled");
        if(null!=enabled&&!"".equals(enabled)) {
            if("1".equals(enabled)) {
                wrapper.eq("enabled", true);
            }else {
                wrapper.eq("enabled", false);
            }
        }
        wrapper.eq("isDel", false);
        //账号类型：APP：前端app用户，BACKEND：后端管理用户
        String type = MapUtils.getString(params, "type");
        if(null!=type&&!"".equals(type)){
            wrapper.eq("type", type);
        }

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
//    @Cacheable(key="'findByUsername::' + #p0")
    public LoginAppUser findByUsername(String username) {
        SysUser sysUser = this.selectByUsername(username);
        return getLoginAppUser(sysUser);
    }

    @Override
//    @Cacheable(key="'findByOpenId::' + #p0")
    public LoginAppUser findByOpenId(String openId) {
        SysUser sysUser = this.selectByOpenId(openId);
        return getLoginAppUser(sysUser);
    }

    @Override
//    @Cacheable(key="'findByMobile::' + #p0")
    public LoginAppUser findByMobile(String mobile) {
        SysUser sysUser = this.selectByMobile(mobile);
        return getLoginAppUser(sysUser);
    }

    @Override
    public LoginAppUser getLoginAppUser(SysUser sysUser) {
        if (sysUser != null) {
            LoginAppUser loginAppUser = new LoginAppUser();
            BeanUtils.copyProperties(sysUser, loginAppUser);

            List<SysRole> sysRoles = roleUserService.findRolesByUserId(sysUser.getId());
            // 设置角色
            loginAppUser.setRoles(sysRoles);

            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<Long> roleIds = sysRoles.parallelStream().map(SuperEntity::getId).collect(Collectors.toSet());
                List<SysMenu> menus = roleMenuMapper.findMenusByRoleIds(roleIds, CommonConstant.PERMISSION);
                if (!CollectionUtils.isEmpty(menus)) {
                    Set<String> permissions = menus.parallelStream().map(p -> p.getPath())
                            .collect(Collectors.toSet());
                    // 设置权限集合
                    loginAppUser.setPermissions(permissions);
                }
            }
            return loginAppUser;
        }
        return null;
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
//    @Cacheable(key="'selectByUsername::' + #p0")
    public SysUser selectByUsername(String username) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("username", username)
        );
        return getUser(users);
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    @Override
//    @Cacheable(key="'selectByMobile::' + #p0")
    public SysUser selectByMobile(String mobile) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("mobile", mobile)
        );
        return getUser(users);
    }

    @Override
//    @Cacheable(key="'selectById::' + #p0")
    public SysUser selectById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 根据openId查询用户
     *
     * @param openId
     * @return
     */
    @Override
//    @Cacheable(key="'selectByOpenId::' + #p0")
    public SysUser selectByOpenId(String openId) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("open_id", openId)
        );
        return getUser(users);
    }

    private SysUser getUser(List<SysUser> users) {
        SysUser user = null;
        if (users != null && !users.isEmpty()) {
            user = users.get(0);
        }
        return user;
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

    @Transactional
    @Override
    public Result updatePassword(Long id, String oldPassword, String newPassword) {
        SysUser sysUser = baseMapper.selectById(id);
        if (StrUtil.isNotBlank(oldPassword)) {
            if (!passwordEncoder.matches(oldPassword, sysUser.getPassword())) {
                return Result.failed("旧密码错误");
            }
        }
        if (StrUtil.isBlank(newPassword)) {
            newPassword = CommonConstant.DEF_USER_PASSWORD;
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(newPassword));
        baseMapper.updateById(user);
        return Result.succeed("修改成功");
    }

    @Transactional
    @Override
    public String resetUpdatePassword(Long id) {
        SysUser sysUser = baseMapper.selectById(id);
        SysUser user = new SysUser();
        user.setId(id);
        //随机生成
        String password = PasswordUtil.getRandomPwd();
        user.setPassword(passwordEncoder.encode(password));
        baseMapper.updateById(user);
        return password;
    }

    @Override
    public Result updateRemark(Long id, String remark) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setRemark(remark);
        int i = baseMapper.updateById(sysUser);
        return i > 0 ? Result.succeed(sysUser, "更新成功") : Result.failed("更新失败");
    }


    /**
     * 查询最近上线列表
     *
     * @return
     */
    @Override
    public List<UserListInfoVo> onlineList(String merchantCode) {
        return baseMapper.onlineList(merchantCode);
    }

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return roleUserService.findRolesByUserId(userId);
    }

    @Override
    public Result updateEnabled(EnabledUserCo params) {
        Long id = params.getId();
        Boolean enabled = params.getEnabled();

        SysUser appUser = baseMapper.selectById(id);
        if (appUser == null) {
            return Result.failed("用户不存在");
        }
        appUser.setEnabled(enabled);
        if (params.getUpdateBy()!=null){
            appUser.setUpdateBy(params.getUpdateBy());
        }
        appUser.setUpdateTime(new Date());
        int i = baseMapper.updateById(appUser);
        log.info("修改用户：{}", appUser);

        return i > 0 ? Result.succeed(appUser, "更新成功") : Result.failed("更新失败");
    }

    @Override
    public Result updateGaKey(Map<String, Object> params) {
        Long id = MapUtils.getLong(params, "id");
        String gaKey = MapUtils.getString(params, "gaKey");

        SysUser appUser = baseMapper.selectById(id);
        if (appUser == null) {
            return Result.failed("用户不存在");
        }
        appUser.setGaKey(gaKey);
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
    public Integer findUserNum(Map<String, Object> params) {
        return baseMapper.findUserNum(params);
    }

    @Override
    public List<SysUser> findListByIds(List<Long> ids) {
        return baseMapper.findListByIds(ids);
    }

    @Override
    public List<SysUserInfoMoneyVo> findListByUserIds(List<Long> userIdList) {
        return baseMapper.findListByUserIds(userIdList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveOrUpdateUser(SysUser sysUser) throws Exception {
        Boolean saveMark = false;
        if (sysUser.getId() == null) {  // 新增加用户
            saveMark = true;
            if (StringUtils.isBlank(sysUser.getType())) {
                sysUser.setType(UserTypeEnum.BACKEND.name());
            }
            if (StringUtils.isBlank(sysUser.getPassword())) {
                sysUser.setPassword(passwordEncoder.encode(CommonConstant.DEF_USER_PASSWORD));
            } else {
                sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            }
            sysUser.setEnabled(Boolean.TRUE);
        }
        String username = sysUser.getUsername();
        boolean result = super.saveOrUpdateIdempotency(sysUser, lock
                , LOCK_KEY_USERNAME + username, new QueryWrapper<SysUser>().eq("username", username)
                , username + "已存在");
        //更新角色
        if (result && StrUtil.isNotEmpty(sysUser.getRoleId())) {
            roleUserService.deleteUserRole(sysUser.getId(), null);
            List roleIds = Arrays.asList(sysUser.getRoleId().split(","));
            if (!CollectionUtils.isEmpty(roleIds)) {
                List<SysRoleUser> roleUsers = new ArrayList<>(roleIds.size());
                roleIds.forEach(roleId -> roleUsers.add(new SysRoleUser(sysUser.getId(), Long.parseLong(roleId.toString()))));
                roleUserService.saveBatch(roleUsers);
            }
        }
        if (saveMark && sysUser.getType().equals(UserTypeEnum.APP.name()) && result) {
            //调用用户钱包接口
            /*SysUserMoney userMoney = new SysUserMoney();
            userMoney.setUserId(sysUser.getId());
            userMoneyService.saveCache(userMoney);*/
        }
        return result ? Result.succeed(sysUser, "操作成功") : Result.failed("操作失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delUser(Long id) {
        roleUserService.deleteUserRole(id, null);
        return baseMapper.deleteById(id) > 0;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(key = "'findByUsername::' + #p0.username"),
//            @CacheEvict(key = "'findByOpenId::' + #p0.openId"),
//            @CacheEvict(key = "'findByMobile::' + #p0.mobile"),
//            @CacheEvict(key = "'selectByUsername::' + #p0.username"),
//            @CacheEvict(key = "'findByOpenId::' + #p0.openId"),
//            @CacheEvict(key = "'selectByMobile::' + #p0.mobile"),
//            @CacheEvict(key = "'selectById::' + #p0.id"),
//    })
    public void cacheEvictUser(SysUser sysUser) {
    }




    /**
     * 查询会员管理列表
     * @param params
     * @return SysUser
     * @Author: Lulu
     * @Date: 2023/2/7
     */
    @Override
    public PageResult<SysUser> findUserList(SysUserCo params) {
        Page<SysUser> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<SysUser> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.like(SysUser::getSiteId, params.getSiteId());
        }
        if (StringUtils.isNotBlank(params.getUserName())){
            wrapper.eq(SysUser::getUsername, params.getUserName());
        }
        if (StringUtils.isNotBlank(params.getMobile())){
            wrapper.eq(SysUser::getMobile, params.getMobile());
        }
        if (params.getVip()!=null){
            wrapper.eq(SysUser::getVip, params.getVip());
        }

        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(SysUser::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(SysUser::getCreateTime, params.getEndTime());
        }

        wrapper.eq(SysUser::getType,UserTypeEnum.APP.name());
        wrapper.orderByDesc(SysUser::getCreateTime);
        Page<SysUser> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<SysUser>builder().data(list.getRecords()).count(total).build();
    }


    /**
     * 添加会员
     * @param user
     * @return
     * @Author: Lulu
     * @Date: 2023/2/7
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveOrUpdateUserInfo(SysUser user) {
        boolean insert =false;
        //新增
        if (user.getId() == null) {
            LambdaQueryWrapper<SysUser> wrapper=new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(user.getUsername())){
                wrapper.eq(SysUser::getUsername, user.getUsername());
            }
            Integer integer = baseMapper.selectCount(wrapper);
            if (integer>0){
                return Result.failed("会员账号已存在");
            }

            if (StringUtils.isBlank(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(CommonConstant.DEF_USER_PASSWORD));
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            String promotionCode = RandomUtil.randomString(PornConstants.Str.RANDOM_BASE_STR, 6);
            user.setPromotionCode(promotionCode);
            user.setNickname(user.getUsername());
            user.setType(UserTypeEnum.APP.name());
            user.setIsReg(UserRegTypeEnum.ADMIN_CREATE.getType());
            user.setEnabled(Boolean.TRUE);
            insert = super.save(user);
            //添加运营报表
            summaryService.addSummaryNewUserNum(user.getSiteId(),user.getSiteCode(),user.getSiteName());
        }else {
            SysUser userInfo = baseMapper.selectById(user.getId());
            if (userInfo == null) {
                return Result.failed("会员不存在");
            }
            //userInfo.setMobile(user.getMobile());
            userInfo.setUpdateBy(user.getUpdateBy());
            userInfo.setSex(user.getSex());
            int i = baseMapper.updateById(userInfo);
            insert = i > 0 ? true : false;
        }
        if (insert) {
            return Result.succeed(user, "操作成功");
        }
        return Result.failed("操作失败");
    }

    @Override
    public Result<UserVipExpireVo> updateUserVipExpire(Long userId, Integer days) {
        SysUser sysUser = baseMapper.selectById(userId);
        if (sysUser == null) {
            return Result.failed("数据不存在");
        }
        Date vipExpire = sysUser.getVipExpire() == null ? new Date() : sysUser.getVipExpire();
        Date date = DateUtil.getDate(vipExpire, days == null ? 0 : days);
        //当前时间/vip到期时间 + vip天数
        sysUser.setVipExpire(date);
        int i = baseMapper.updateById(sysUser);

        UserVipExpireVo UserVipExpireVo=new UserVipExpireVo();
        UserVipExpireVo.setBeforeExpire(vipExpire);
        UserVipExpireVo.setAfterExpire(date);
        //记录VIP最新过期时间 add by year
        asyncService.setVipExpire(date, sysUser.getId());
        return i>0 ? Result.succeed(UserVipExpireVo,"操作成功") :Result.failed("操作失败");
    }

    /**
     * 查询会员推广数据
     * @param params
     * @return SysUser
     * @Author: Lulu
     * @Date: 2023/2/8
     */
    @Override
    public PageResult<UserExtensionListInfoVo> findUserExtensionList(SysUserExtensionCo params) {
        Page<UserExtensionListInfoVo> page = new Page<>(params.getPage(), params.getLimit());
        List<UserExtensionListInfoVo> list  =  baseMapper.findUserExtensionList(page, params);
        return PageResult.<UserExtensionListInfoVo>builder().data(list).count(page.getTotal()).build();
    }



}