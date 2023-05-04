package com.central.backend.mapper;

import com.central.backend.model.vo.KpnActorVO;
import com.central.common.model.KpnActor;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 演员列表
 * 
 * @author yixiu
 * @date 2023-02-03 16:31:09
 */
@Mapper
public interface KpnActorMapper extends SuperMapper<KpnActor> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<KpnActorVO> findList(Page<KpnActorVO> page, @Param("p") Map<String, Object> params);
}
