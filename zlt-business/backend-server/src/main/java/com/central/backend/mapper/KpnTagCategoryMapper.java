package com.central.backend.mapper;

import com.central.backend.model.vo.KpnTagCategoryVO;
import com.central.common.model.KpnTagCategory;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 影片标签分类
 * 
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Mapper
public interface KpnTagCategoryMapper extends SuperMapper<KpnTagCategory> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<KpnTagCategoryVO> findList(Page<KpnTagCategoryVO> page, @Param("p") Map<String, Object> params);

    List<KpnTagCategory> findListAll();
}
