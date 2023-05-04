package com.central.backend.mapper;

import com.central.backend.model.vo.KpnTagVO;
import com.central.backend.vo.CategoryVo;
import com.central.backend.vo.KpnTagVo;
import com.central.common.model.KpnTag;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 影片标签
 * 
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Mapper
public interface KpnTagMapper extends SuperMapper<KpnTag> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<KpnTagVO> findList(Page<KpnTagVO> page, @Param("p") Map<String, Object> params);


    List<KpnTagVo> findTagList( @Param("p")  Map<String, Object> params);
    List<CategoryVo> findTagCategoryList( );

}
