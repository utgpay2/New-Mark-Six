package com.central.backend.mapper;

import com.central.common.model.KpnSiteApp;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 站点app更新配置
 * 
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Mapper
public interface KpnSiteAppMapper extends SuperMapper<KpnSiteApp> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<KpnSiteApp> findList(Page<KpnSiteApp> page, @Param("p") Map<String, Object> params);
}
