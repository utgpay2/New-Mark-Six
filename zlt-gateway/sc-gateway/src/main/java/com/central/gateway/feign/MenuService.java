package com.central.gateway.feign;

import com.central.common.constant.ServiceNameConstants;
import com.central.common.model.SysMenu;
import com.central.common.model.SysUser;
import com.central.gateway.feign.fallback.MenuServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author zlt
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = MenuServiceFallbackFactory.class, decode404 = true)
public interface MenuService {
	/**
	 * 角色菜单列表
	 * @param roleCodes
	 */
	@GetMapping(value = "/menus/{roleCodes}")
	List<SysMenu> findByRoleCodes(@PathVariable("roleCodes") String roleCodes);

	/**
	 * feign rpc访问远程/users/{username}接口
	 * 查询用户实体对象SysUser
	 *
	 * @param username
	 * @return
	 */
	@GetMapping(value = "/users/name/{username}")
	SysUser selectByUsername(@PathVariable("username") String username);


}
