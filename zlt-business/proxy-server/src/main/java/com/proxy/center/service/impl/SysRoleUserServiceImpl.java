package com.proxy.center.service.impl;

import com.proxy.center.co.SysRoleUser;
import com.proxy.center.mapper.SysUserRoleMapper;
import com.proxy.center.service.ISysRoleUserService;
import com.central.common.model.SysRole;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zlt
 */
@Slf4j
@Service
public class SysRoleUserServiceImpl extends SuperServiceImpl<SysUserRoleMapper, SysRoleUser> implements ISysRoleUserService {
 	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public int deleteUserRole(Long userId, Long roleId) {
		return sysUserRoleMapper.deleteUserRole(userId, roleId);
	}

	@Override
	public int saveUserRoles(Long userId, Long roleId) {
		return sysUserRoleMapper.saveUserRoles(userId, roleId);
	}

	@Override
	public List<SysRole> findRolesByUserId(Long userId) {
		return sysUserRoleMapper.findRolesByUserId(userId);
	}

	@Override
	public Integer findRolesId(Long roleId) {
		return sysUserRoleMapper.findRolesId(roleId);
	}

	@Override
	public List<SysRole> findRolesByUserIds(List<Long> userIds) {
		return sysUserRoleMapper.findRolesByUserIds(userIds);
	}

	@Override
	public SysUser getStationOwenrBySiteId(Integer siteId) {

		return baseMapper.getStationOwenrBySiteId(siteId);
	}
}
