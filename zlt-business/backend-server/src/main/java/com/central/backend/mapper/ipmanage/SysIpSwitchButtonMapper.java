package com.central.backend.mapper.ipmanage;

import com.central.common.model.ipmanage.SysIpSwitchButton;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yixiu
 * @date 2023-02-03 15:07:56
 */
@Mapper
public interface SysIpSwitchButtonMapper extends SuperMapper<SysIpSwitchButton> {
    /**
     * 查询用户列表
     * @return
     */
    List<SysIpSwitchButton> findList();
}
