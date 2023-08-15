package com.proxy.center.service;

import com.proxy.center.co.GaBindCo;
import com.proxy.center.model.dto.SysAdminUserDto;
import com.proxy.center.model.dto.SysAdminUserEnabledDto;
import com.central.common.model.*;
import com.central.common.service.ISuperService;

import java.math.BigDecimal;
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
	Result updateEnabled(SysAdminUserEnabledDto enabledDto);
	Result updateVerify(Long id);
	Result updateGaBind(GaBindCo params);
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

	Result getProxyBySiteCode(String siteCode);


	/**
	 * 增加奖励M币数
	 *
	 * @param sysUser  登录会员
	 * @param rewardMb 奖励M币数
	 */
	void addRewardMb(SysUser sysUser, BigDecimal rewardMb);

	void addRewardTestMb(SysUser sysUser, BigDecimal rewardMb);
}
