package com.central.backend.service;

import com.central.backend.co.GaBindCo;
import com.central.backend.model.dto.SysAdminUserDto;
import com.central.backend.model.dto.SysAdminUserEnabledDto;
import com.central.common.model.*;
import com.central.common.service.ISuperService;

import java.util.Map;

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
	Result updateEnabled(SysAdminUserEnabledDto enabledDto);
	Result updateVerify(Long id);
	Result updateGaBind(GaBindCo params);
	boolean delUser(Long id);
}
