package com.central.backend.service;

import com.central.backend.vo.SysRoleVo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysRole;
import com.central.common.service.ISuperService;
import com.central.backend.co.RolePageCo;

import java.util.List;

/**
 * @author zlt
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public interface ISysRoleService extends ISuperService<SysRole> {
	void saveRole(SysRole sysRole) throws Exception;

	void deleteRole(Long id);

	/**
	 * 角色列表
	 * @param params
	 * @return
	 */
	PageResult<SysRoleVo> findRoles(RolePageCo params);

	/**
	 * 新增或更新角色
	 * @param sysRole
	 * @return Result
	 */
	Result saveOrUpdateRole(SysRole sysRole) throws Exception;

	/**
	 * 查询所有角色
	 * @return
	 */
	List<SysRole> findAll();

	SysRole findSysRoleByName(String name);

	SysRole findSysRoleByCode(String code);
}
