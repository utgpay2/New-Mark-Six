package com.proxy.center.mapper.ipmanage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.ipmanage.SysWhiteIp;
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
public interface SysWhiteIpMapper extends SuperMapper<SysWhiteIp> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<SysWhiteIp> findList(Page<SysWhiteIp> page, @Param("p") Map<String, Object> params);
}
