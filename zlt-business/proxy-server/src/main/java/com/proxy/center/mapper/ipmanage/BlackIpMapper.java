package com.proxy.center.mapper.ipmanage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.model.vo.BlackIpVo;
import com.central.common.model.ipmanage.BlackIp;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Mapper
public interface BlackIpMapper extends SuperMapper<BlackIp> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<BlackIpVo> findList(Page<BlackIpVo> page, @Param("p") Map<String, Object> params);
}
