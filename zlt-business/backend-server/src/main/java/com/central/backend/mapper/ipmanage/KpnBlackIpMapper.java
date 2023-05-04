package com.central.backend.mapper.ipmanage;

import com.central.backend.model.vo.KpnBlackIpVO;
import com.central.common.model.ipmanage.KpnBlackIp;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Mapper
public interface KpnBlackIpMapper extends SuperMapper<KpnBlackIp> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<KpnBlackIpVO> findList(Page<KpnBlackIpVO> page, @Param("p") Map<String, Object> params);
}
