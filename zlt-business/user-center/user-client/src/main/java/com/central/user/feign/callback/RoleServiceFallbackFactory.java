package com.central.user.feign.callback;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysRole;
import com.central.user.feign.RoleService;
import com.central.user.model.co.RolePageCo;
import com.central.user.model.co.SysRoleCo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;
import java.util.Map;


@Slf4j
public class RoleServiceFallbackFactory implements FallbackFactory<RoleService> {
    @Override
    public RoleService create(Throwable throwable) {
        return new RoleService() {
            @Override
            public  Result<PageResult<SysRole>> findRoles(RolePageCo params) {
                log.error("findRoles查询角色异常:{}", params, throwable);
                return Result.failed("查询角色失败");
            }

            @Override
            public Result<List<SysRole>> findAll() {
                log.error("findAll查询角色异常",  throwable);
                return Result.failed("查询角色失败");
            }

            @Override
            public Result saveOrUpdate(SysRoleCo sysRole) throws Exception {
                log.error("saveOrUpdate编辑角色异常:{}", sysRole, throwable);
                return Result.failed("编辑角色失败");
            }

            @Override
            public Result deleteRole(Long id) {
                log.error("deleteRole删除角色异常:{}", id, throwable);
                return Result.failed("删除角色失败");
            }
        };
    }
}
