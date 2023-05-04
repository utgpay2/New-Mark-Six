package com.central.backend.mapper;

import com.central.backend.vo.MovieVo;
import com.central.common.model.KpnSiteMovie;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 站点影片
 * 
 * @author yixiu
 * @date 2023-02-20 17:00:56
 */
@Mapper
public interface KpnSiteMovieMapper extends SuperMapper<KpnSiteMovie> {
//    /**
//     * 分页查询用户列表
//     * @param page
//     * @param params
//     * @return
//     */
//    List<KpnSiteMovieVO> findList(Page<KpnSiteMovieVO> page, @Param("p") Map<String, Object> params);

    int updateBatchStatusById(List<KpnSiteMovie> list);
    int updateBatchPayTypeById(List<KpnSiteMovie> list);


    List<MovieVo> findMovieList(Page<MovieVo> page, @Param("p") Map<String, Object> params);
}
