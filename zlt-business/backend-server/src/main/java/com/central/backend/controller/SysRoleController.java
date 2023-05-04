package com.central.backend.controller;

import com.central.backend.co.RolePageCo;
import com.central.backend.service.ISysRoleService;
import com.central.backend.service.ISysRoleUserService;
import com.central.backend.vo.SysRoleVo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author 作者 owen E-mail: 624191343@qq.com 角色管理
 */
@Slf4j
@RestController
@Api(tags = "角色模块api")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;


    @Autowired
    private ISysRoleUserService roleUserService;

    /**
     * 后台管理查询角色
     * 
     * @param params
     * @return
     */
    @ApiOperation(value = "后台管理查询角色")
    @GetMapping("/roles")
    public Result<PageResult<SysRoleVo>> findRoles(@ModelAttribute RolePageCo params) {
        PageResult<SysRoleVo> roles = sysRoleService.findRoles(params);
        return Result.succeed(roles);
    }

    /**
     * 用户管理查询所有角色
     * 
     * @return
     */
    @ApiOperation(value = "后台管理查询角色")
    @GetMapping("/allRoles")
    public Result<List<SysRole>> findAll() {
        List<SysRole> result = sysRoleService.findAll();
        return Result.succeed(result);
    }

    /**
     * 角色新增或者更新
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/roles/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysRole sysRole){
        if (StringUtils.isBlank(sysRole.getName())) {
            return Result.failed("名称不能为空");
        }
        if (StringUtils.isBlank(sysRole.getCode())) {
            return Result.failed("code不能为空");
        }

        SysRole sysRoleByName = sysRoleService.findSysRoleByName(sysRole.getName());
        if (Objects.nonNull(sysRoleByName)) {
            return Result.failed("角色名称重复");
        }
        SysRole sysRoleByCode = sysRoleService.findSysRoleByCode(sysRole.getCode());
        if (Objects.nonNull(sysRoleByCode)) {
            return Result.failed("角色code重复");
        }
        Result result = null;
        try {
            result = sysRoleService.saveOrUpdateRole(sysRole);
        } catch (Exception e) {
            return Result.failed("新增失败");
        }
        return result;
    }

    /**
     * 后台管理删除角色 delete /role/1
     *
     * @param id
     */
    @ApiOperation(value = "后台管理删除角色")
    @DeleteMapping("/roles/{id}")
    public Result deleteRole(@PathVariable Long id) {
        try {
            if (id == 1L) {
                return Result.failed("管理员不可以删除");
            }
            //判断是否有判定得用户
            Integer rolesId = roleUserService.findRolesId(id);
            if (rolesId>0){
                return Result.failed("该角色有绑定关系,暂不可以删除");
            }
            sysRoleService.deleteRole(id);
            return Result.succeed("操作成功");
        } catch (Exception e) {
            log.error("role-deleteRole-error", e);
            return Result.failed("操作失败");
        }
    }
}
