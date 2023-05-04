package com.central.user.feign;

import com.central.common.constant.ServiceNameConstants;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysMenu;
import com.central.common.model.SysUser;
import com.central.user.feign.callback.UserServiceFallbackFactory;
import com.central.user.model.co.SysMenuCo;
import com.central.user.model.co.SysMenuDistributionCo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zlt
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface MenuService {



    @GetMapping("/menus/{roleId}/menus")
    List<SysMenu> findMenusByRoleId(@PathVariable("roleId")   Long roleId);

    /**
     * 给角色分配菜单
     */
    @PostMapping("/menus/granted")
    Result setMenuToRole(@RequestBody SysMenuDistributionCo sysMenu);




    /**
     * 删除菜单
     *
     * @param id
     */
    @DeleteMapping("/menus/{id}")
     Result delete(@PathVariable("id")  Long id) ;

    /**
     * 查询所有菜单
     * @return
     */
    @GetMapping("/menus/findAlls")
    PageResult<SysMenu> findAlls() ;

    /**
     * 菜单管理：修改按钮获取菜单以及顶级菜单
     * @return
     */
    @GetMapping("/menus/findOnes")
    PageResult<SysMenu> findOnes() ;


    /**
     * 添加菜单 或者 更新
     *
     * @param menu
     * @return
     */
    @PostMapping("/menus/saveOrUpdate")
     Result saveOrUpdate(@RequestBody SysMenuCo menu) ;

    @PostMapping("/menus/current")
    Result<List<SysMenu>> findMyMenu(SysUser user);
}
