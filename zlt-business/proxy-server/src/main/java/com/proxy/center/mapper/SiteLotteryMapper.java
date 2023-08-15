package com.proxy.center.mapper;

import com.central.common.model.SiteLottery;
import com.central.common.vo.SiteLotteryVo;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 彩种下注分类
 * 
 * @author zlt
 * @date 2023-05-11 18:50:09
 */
@Mapper
public interface SiteLotteryMapper extends SuperMapper<SiteLottery> {
    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    List<SiteLotteryVo> findList(@Param("p") Map<String, Object> params);
}
