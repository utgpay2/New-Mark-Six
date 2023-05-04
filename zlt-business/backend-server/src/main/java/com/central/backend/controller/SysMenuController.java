package com.central.backend.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.central.backend.co.SysMenuDistributionCo;
import com.central.backend.service.ISysMenuService;
import com.central.common.constant.CommonConstant;
import com.central.common.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@RestController
@Api(tags = "菜单模块api")
@Slf4j
@RequestMapping("/menus")
public class SysMenuController {
    @Autowired
    private ISysMenuService menuService;

    /**
     * 两层循环实现建树
     *
     * @param sysMenus
     * @return
     */
    public static List<SysMenu> treeBuilder(List<SysMenu> sysMenus) {
        List<SysMenu> menus = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            if (ObjectUtil.equal(-1L, sysMenu.getParentId())) {
                menus.add(sysMenu);
            }
            for (SysMenu menu : sysMenus) {
                if (menu.getParentId().equals(sysMenu.getId())) {
                    if (sysMenu.getSubMenus() == null) {
                        sysMenu.setSubMenus(new ArrayList<>());
                    }
                    sysMenu.getSubMenus().add(menu);
                }
            }
        }
        return menus;
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        try {
            menuService.removeById(id);
            return Result.succeed("操作成功");
        } catch (Exception ex) {
            log.error("memu-delete-error", ex);
            return Result.failed("操作失败");
        }
    }

    @ApiOperation(value = "根据roleId获取对应的菜单")
    @GetMapping("/{roleId}/menus")
    public List<SysMenu> findMenusByRoleId(@PathVariable Long roleId) {
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(roleId);
        // 获取该角色对应的菜单
        List<SysMenu> roleMenus = menuService.findByRoles(roleIds);
        return treeBuilder(roleMenus);
    }

    @ApiOperation(value = "根据roleCodes获取对应的权限")
    @SuppressWarnings("unchecked")
    @Cacheable(value = "menu", key = "#roleCodes")
    @GetMapping("/{roleCodes}")
    public List<SysMenu> findMenuByRoles(@PathVariable String roleCodes) {
        List<SysMenu> result = null;
        if (StringUtils.isNotEmpty(roleCodes)) {
            Set<String> roleSet = (Set<String>)Convert.toCollection(HashSet.class, String.class, roleCodes);
            result = menuService.findByRoleCodes(roleSet, CommonConstant.PERMISSION);
        }
        return result;
    }

    /**
     * 给角色分配菜单
     */
    @ApiOperation(value = "角色分配菜单")
    @PostMapping("/granted")
    public Result setMenuToRole(@RequestBody SysMenuDistributionCo sysMenu) {
        menuService.setMenuToRole(sysMenu.getRoleId(), sysMenu.getMenuIds());
        return Result.succeed("操作成功");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/findAlls")
    public PageResult<SysMenu> findAlls() {
        List<SysMenu> list = menuService.findAll();
        List<SysMenu> sysMenus = treeBuilder(list);
        return PageResult.<SysMenu>builder().data(sysMenus).count((long)list.size()).build();
    }

    @ApiOperation(value = "获取菜单以及顶级菜单")
    @GetMapping("/findOnes")
    public PageResult<SysMenu> findOnes() {
        List<SysMenu> list = menuService.findOnes();
        return PageResult.<SysMenu>builder().data(list).count((long)list.size()).build();
    }

    /**
     * 添加菜单 或者 更新
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysMenu menu) {
        try {
            menuService.saveOrUpdate(menu);
            return Result.succeed("操作成功");
        } catch (Exception ex) {
            log.error("memu-saveOrUpdate-error", ex);
            return Result.failed("操作失败");
        }
    }

    /**
     * 当前登录用户的菜单
     *
     * @return
     */
    @PostMapping("/current")
    @ApiOperation(value = "查询当前用户菜单")
    public Result<List<SysMenu>> findMyMenu(@RequestBody SysUser user) {
        List<SysRole> roles = user.getRoles();
        if (CollectionUtil.isEmpty(roles)) {
            return Result.failed("数据为空");
        }
        List<SysMenu> menus = menuService.findByRoleCodes(
                roles.parallelStream().map(SysRole::getCode).collect(Collectors.toSet()), CommonConstant.MENU);
        List<SysMenu> sysMenus = treeBuilder(menus);
        return Result.succeed(sysMenus);
    }

}
