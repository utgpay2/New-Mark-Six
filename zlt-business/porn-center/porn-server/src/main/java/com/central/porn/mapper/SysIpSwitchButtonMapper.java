package com.central.porn.mapper;

import com.central.common.model.ipmanage.SysIpSwitchButton;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
