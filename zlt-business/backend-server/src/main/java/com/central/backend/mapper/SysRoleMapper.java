package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.RolePageCo;
import com.central.backend.vo.SysRoleVo;
import com.central.common.model.SysRole;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zlt
 * 角色
 */
@Mapper
public interface SysRoleMapper extends SuperMapper<SysRole> {
	List<SysRoleVo> findList(Page<SysRoleVo> page, @Param("r") RolePageCo params);

	List<SysRole> findAll();
}
