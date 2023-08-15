package com.proxy.center.mapper.pay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.pay.SiteBankCard;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 收款银行卡配置
 * 
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Mapper
public interface SiteBankCardMapper extends SuperMapper<SiteBankCard> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<SiteBankCard> findList(Page<SiteBankCard> page, @Param("p") Map<String, Object> params);
    List<SiteBankCard> findList(@Param("p") Map<String, Object> params);
}
