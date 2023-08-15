package com.proxy.center.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.SiteServe;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商户客服配置
 * 
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Mapper
public interface SiteServeMapper extends SuperMapper<SiteServe> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<SiteServe> findList(Page<SiteServe> page, @Param("p") Map<String, Object> params);
    List<SiteServe> findList(@Param("p") Map<String, Object> params);
}
