package com.central.backend.mapper.pay;

import com.central.db.mapper.SuperMapper;
import com.central.common.model.pay.KpnSiteBankCard;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 收款银行卡配置
 * 
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Mapper
public interface KpnSiteBankCardMapper extends SuperMapper<KpnSiteBankCard> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<KpnSiteBankCard> findList(Page<KpnSiteBankCard> page, @Param("p") Map<String, Object> params);
    List<KpnSiteBankCard> findList(@Param("p") Map<String, Object> params);
}
