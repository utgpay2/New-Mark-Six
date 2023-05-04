package com.central.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.CommonConstant;
import com.central.common.lock.DistributedLock;
import com.central.common.model.*;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.user.mapper.SysRoleMenuMapper;
import com.central.user.mapper.SysUserMapper;
import com.central.user.model.SysRoleUser;
import com.central.user.model.co.GaBindCo;
import com.central.user.model.co.SysUserListCo;
import com.central.user.model.vo.SysUserInfoMoneyVo;
import com.central.user.service.ISysRoleUserService;
import com.central.user.service.ISysUserService;
import com.central.user.util.PasswordUtil;
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
 * user服务
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
    private DistributedLock lock;

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

    /**
     * 后台管理员列表
     *
     * @param params
     * @return
     */
    @Override
    public PageResult<SysUser> findUsers(SysUserListCo params) {
        Page<SysUser> page = new Page<>(params.getPage(), params.getLimit());
        List<SysUser> list = baseMapper.adminFindList(page, params);
        long total = page.getTotal();
        return PageResult.<SysUser>builder().data(list).count(total).build();
    }


/*    @Override
    public PageResult<SysUser> findUsers(SysUserListCo params) {
        Page<SysUser> page = new Page<>(params.getPage(), params.getLimit());
        List<SysUser> list = baseMapper.userFindList(page, params);
        long total = page.getTotal();
        if (total > 0) {
            List<Long> userIds = list.stream().map(SysUser::getId).collect(Collectors.toList());

            List<SysRole> sysRoles = roleUserService.findRolesByUserIds(userIds);
            Map<Long, List<SysRole>> sysRolesMap = sysRoles.stream().collect(Collectors.groupingBy(SysRole::getUserId));
            list.forEach(info ->{
                List<SysRole> proxyHomes = sysRolesMap.get(info.getId());
                if (proxyHomes!=null){
                    info.setRoles(proxyHomes);
                    info.setRoleId(proxyHomes.get(0).getId().toString());
                }
            });
        }
        return PageResult.<SysUser>builder().data(list).count(total).build();
    }*/

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return roleUserService.findRolesByUserId(userId);
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
            //创建钱包
           /* SysUserMoney userMoney = new SysUserMoney();
            userMoney.setUserId(sysUser.getId());
            userMoney.setMoney(BigDecimal.ZERO);
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
}