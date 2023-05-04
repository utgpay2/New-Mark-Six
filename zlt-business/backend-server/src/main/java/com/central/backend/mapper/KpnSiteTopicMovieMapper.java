package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.vo.SiteMovieListVo;
import com.central.common.model.KpnSiteTopicMovie;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
 * @Author: Lulu
 * @Date: 2023/2/20
 */
@Mapper
public interface KpnSiteTopicMovieMapper extends SuperMapper<KpnSiteTopicMovie> {

   List<SiteMovieListVo> findSiteMovieList(Page<SiteMovieListVo> page, @Param("p") Map<String, Object> params);


}
