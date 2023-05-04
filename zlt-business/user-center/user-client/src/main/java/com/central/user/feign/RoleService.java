package com.central.user.feign;

import com.central.common.constant.ServiceNameConstants;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysRole;
import com.central.user.feign.callback.UserServiceFallbackFactory;
import com.central.user.model.co.RolePageCo;
import com.central.user.model.co.SysRoleCo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zlt
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface RoleService {


    /**
     * 后台管理查询角色
     * @param params
     * @return
     */
    @GetMapping("/roles")
    Result<PageResult<SysRole>> findRoles(@SpringQueryMap RolePageCo params);



    @GetMapping("/allRoles")
    Result<List<SysRole>> findAll() ;

    /**
     * 角色新增或者更新
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/roles/saveOrUpdate")
    Result saveOrUpdate(@RequestBody SysRoleCo sysRole) throws Exception ;

    /**
     * 后台管理删除角色
     * delete /role/1
     *
     * @param id
     */
    @DeleteMapping("/roles/{id}")
     Result deleteRole(@PathVariable("id")  Long id) ;
}
