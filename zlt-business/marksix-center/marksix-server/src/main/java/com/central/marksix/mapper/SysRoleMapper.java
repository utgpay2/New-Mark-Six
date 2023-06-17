package com.central.marksix.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

}
