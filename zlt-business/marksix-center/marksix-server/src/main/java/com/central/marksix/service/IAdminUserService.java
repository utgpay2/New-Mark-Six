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
	SysUser selectById(Long id);
	String resetUpdatePassword(Long id);
	Result updatePassword(Long id, String oldPassword, String newPassword);

	Result updateVerify(Long id);

	boolean delUser(Long id);
	/**
	 * 用户分配角色
	 * @param id
	 * @param roleIds
	 */
	void setRoleToUser(Long id, Set<Long> roleIds);
	/**
	 * 用户角色列表
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(Long userId);

    LoginAppUser findByUsername(String username);

	Result login(String username, String password, String verifyCode);

	SysUser getMerchantAdministrator(String siteCode);
}
