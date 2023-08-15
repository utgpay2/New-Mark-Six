package com.proxy.center.service;

import com.proxy.center.co.SysRoleUser;
import com.central.common.model.SysRole;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;

/**
 * @author zlt
 */
public interface ISysRoleUserService extends ISuperService<SysRoleUser> {
	int deleteUserRole(Long userId, Long roleId);

	int saveUserRoles(Long userId, Long roleId);

	/**
	 * 根据用户id获取角色
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(Long userId);


	Integer findRolesId(Long roleId);


	/**
	 * 根据用户ids 获取
	 *
	 * @param userIds
	 * @return
	 */
	List<SysRole> findRolesByUserIds(List<Long> userIds);

    SysUser getStationOwenrBySiteId(Integer siteId);
}
