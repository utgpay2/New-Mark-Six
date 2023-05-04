package com.central.gateway.feign.fallback;

import cn.hutool.core.collection.CollectionUtil;
import com.central.common.model.SysMenu;
import com.central.common.model.SysUser;
import com.central.gateway.feign.MenuService;
import org.springframework.cloud.openfeign.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * menuService降级工场
 *
 * @author zlt
 * @date 2019/1/18
 */
@Slf4j
@Component
public class MenuServiceFallbackFactory implements FallbackFactory<MenuService> {
    @Override
    public MenuService create(Throwable throwable) {
        return new MenuService() {
            @Override
            public List<SysMenu> findByRoleCodes(String roleCodes) {
                log.error("调用findByRoleCodes异常：{}", roleCodes, throwable);
                return CollectionUtil.newArrayList();
            }

            @Override
            public SysUser selectByUsername(String username) {
                log.error("调用selectByUsername异常：{}", username, throwable);
                return null;
            }
        };
    }
}
