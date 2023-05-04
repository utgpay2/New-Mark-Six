package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.SysUserMoney;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户钱包表
 * 
 * @author zlt
 * @date 2021-12-03 19:31:47
 */
@Mapper
public interface SysUserMoneyMapper extends SuperMapper<SysUserMoney> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<SysUserMoney> findList(Page<SysUserMoney> page, @Param("p") Map<String, Object> params);

    BigDecimal getSumMoneyByParent(@Param("parent") String parent);

}
