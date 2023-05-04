package com.central.porn.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.ipmanage.KpnBlackIp;
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
public interface KpnBlackIpMapper extends SuperMapper<KpnBlackIp> {
}
