package com.central.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.SysRole;
import com.central.db.mapper.SuperMapper;
import com.central.user.model.co.RolePageCo;
import com.central.user.model.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author zlt
 * 角色
 */
@Mapper
public interface SysRoleMapper extends SuperMapper<SysRole> {
	List<SysRoleVo> findList(Page<SysRoleVo> page, @Param("r") RolePageCo params);

	List<SysRole> findAll();
}
