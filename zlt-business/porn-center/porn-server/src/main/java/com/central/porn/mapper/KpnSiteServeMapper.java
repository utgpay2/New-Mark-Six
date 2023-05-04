package com.central.porn.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.KpnSiteServe;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 站点客服配置
 * 
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Mapper
public interface KpnSiteServeMapper extends SuperMapper<KpnSiteServe> {
}
