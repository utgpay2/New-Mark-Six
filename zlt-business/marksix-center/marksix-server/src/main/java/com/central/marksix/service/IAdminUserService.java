package com.central.marksix.service;


import com.central.common.model.*;
import com.central.common.service.ISuperService;
import com.central.marksix.dto.SysAdminUserDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yixiu
 */
public interface IAdminUserService extends ISuperService<SysUser> {
	PageResult<SysUser> findList(Map<String, Object> params, SysUser user);
	/**
	 * 新增管理员
	 * @param adminUserVo,sysUser
	 * @return
	 */
	Result saveOrUpdateAdminInfo(SysAdminUserDto adminUserVo, SysUser sysUser);
	/**
	 * 用户分配角色
	 * @param id
	 * @param roleIds
	 */
	void setRoleToUser(Long id, Set<Long> roleIds);

    LoginAppUser findByUsername(String username);

	SysUser getMerchantAdministrator(String siteCode);


}
