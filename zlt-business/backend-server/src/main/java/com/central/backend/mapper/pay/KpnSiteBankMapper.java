package com.central.backend.mapper.pay;

import com.central.common.model.pay.KpnSiteBank;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 收款银行渠道
 * 
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Mapper
public interface KpnSiteBankMapper extends SuperMapper<KpnSiteBank> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<KpnSiteBank> findList(Page<KpnSiteBank> page, @Param("p") Map<String, Object> params);
    List<KpnSiteBank> findList(@Param("p") Map<String, Object> params);
}
