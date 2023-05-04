package com.central.backend.controller;

import cn.hutool.core.collection.CollUtil;
import com.central.common.annotation.LoginUser;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysMenu;
import com.central.common.model.SysUser;
import com.central.common.utils.I18nUtil;
import com.central.user.feign.MenuService;
import com.central.user.model.co.SysMenuCo;
import com.central.user.model.co.SysMenuDistributionCo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
// @Api(tags = "菜单管理")
@Slf4j
@RequestMapping("/platform/menu")
public class SysMenuControllerBackup {

    @Resource
    private MenuService menuService;

    /**
     * 根据roleId获取对应的菜单
     * 
     * @param roleId
     * @return
     */
    @ApiOperation(value = "根据roleId获取对应的菜单")
    @GetMapping("/menus/{roleId}/menus")
    public Result<List<SysMenu>> findMenusByRoleId(@PathVariable Long roleId) {
        List<SysMenu> listResult = menuService.findMenusByRoleId(roleId);
        if (CollUtil.isNotEmpty(listResult)) {
            listResult.forEach(sysMenu -> {
                 sysMenu.setName(I18nUtil.getBackendValue(sysMenu.getName()));
            });
            return Result.succeed(listResult);
        }
        return Result.succeed(listResult);
    }

    @ApiOperation(value = "获取菜单以及顶级菜单")
    @GetMapping("/menus/findOnes")
    public Result<PageResult<SysMenu>> findOnes() {
        PageResult<SysMenu> result = menuService.findOnes();
        if (Objects.nonNull(result) && CollUtil.isNotEmpty(result.getData())) {
            result.getData().forEach(sysMenu -> {
                sysMenu.setName(I18nUtil.getBackendValue(sysMenu.getName()));
            });
            return Result.succeed(result);
        }
        return Result.succeed(result);
    }

    /**
     * 给角色分配菜单
     */
    @ApiOperation(value = "角色分配菜单")
    @PostMapping("/menus/granted")
    public Result setMenuToRole(@RequestBody SysMenuDistributionCo sysMenu) {
        return menuService.setMenuToRole(sysMenu);
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/menus/{id}")
    public Result delete(@PathVariable Long id) {
        return menuService.delete(id);
    }

    /**
     * 查询所有菜单
     *
     */
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus/findAlls")
    public Result<PageResult<SysMenu>> findAlls() {
        PageResult<SysMenu> result = menuService.findAlls();
        if (Objects.nonNull(result) && CollUtil.isNotEmpty(result.getData())) {
            result.getData().forEach(sysMenu -> {
                sysMenu.setName(I18nUtil.getBackendValue(sysMenu.getName()));
            });
            return Result.succeed(result);
        }
        return Result.succeed(result);
    }

    /**
     * 添加菜单 或者 更新
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("/menussaveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysMenuCo menu) {
        return menuService.saveOrUpdate(menu);
    }

    /**
     * 当前登录用户的菜单
     *
     * @return
     */
    @PostMapping("/menus/current")
    @ApiOperation(value = "查询当前用户菜单")
    public Result<List<SysMenu>> findMyMenu(@LoginUser SysUser user) {
        Result<List<SysMenu>> myMenu = menuService.findMyMenu(user);
        if (myMenu.getResp_code() == 0 && CollUtil.isNotEmpty(myMenu.getDatas())) {
            List<SysMenu> datas = myMenu.getDatas();
            datas.forEach(sysMenu -> {
                sysMenu.setName(I18nUtil.getBackendValue(sysMenu.getName()));
                for (SysMenu subMenu : sysMenu.getSubMenus()) {
                    subMenu.setName(I18nUtil.getBackendValue(subMenu.getName()));
                }
            });
            return Result.succeed(datas);
        }
        return myMenu;
    }
}
