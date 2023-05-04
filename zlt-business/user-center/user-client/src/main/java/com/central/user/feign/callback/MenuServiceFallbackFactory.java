package com.central.user.feign.callback;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysMenu;
import com.central.common.model.SysUser;
import com.central.user.feign.MenuService;
import com.central.user.model.co.SysMenuCo;
import com.central.user.model.co.SysMenuDistributionCo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
public class MenuServiceFallbackFactory implements FallbackFactory<MenuService> {
    @Override
    public MenuService create(Throwable throwable) {
        return new MenuService() {

            @Override
            public List<SysMenu> findMenusByRoleId(Long roleId) {
                log.error("findMenusByRoleId查询菜单异常:{}", roleId, throwable);
                return new ArrayList<>();
            }

            @Override
            public Result setMenuToRole(SysMenuDistributionCo sysMenu) {
                log.error("setMenuToRole分配菜单异常:{}", sysMenu, throwable);
                return Result.failed("分配菜单失败");
            }

            @Override
            public Result delete(Long id) {
                log.error("delete删除菜单异常:{}", id, throwable);
                return Result.failed("删除菜单失败");
            }

            @Override
            public PageResult<SysMenu> findAlls() {
                log.error("findAlls查询全部菜单异常", throwable);
                return new PageResult();
            }

            @Override
            public PageResult<SysMenu> findOnes() {
                log.error("findOnes获取菜单异常", throwable);
                return new PageResult();
            }

            @Override
            public Result saveOrUpdate(SysMenuCo menu) {
                log.error("saveOrUpdate编辑菜单异常:{}", menu, throwable);
                return Result.failed("编辑菜单失败");
            }

            @Override
            public   Result<List<SysMenu>> findMyMenu(SysUser user) {
                log.error("findMyMenu查询当前登录用户菜单异常", throwable);
                return Result.failed("查询当前登录用户菜单失败");
            }

        };
    }
}
