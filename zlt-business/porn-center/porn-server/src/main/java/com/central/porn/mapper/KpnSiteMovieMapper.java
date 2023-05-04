package com.central.porn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.central.common.model.KpnSiteMovie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KpnSiteMovieMapper extends BaseMapper<KpnSiteMovie> {

    /**
     * 按关键词查询
     *
     * @param sid      站点id
     * @param keywords 关键词
     * @return
     */
    List<KpnSiteMovie> getByKeyWords(@Param("sid") Long sid, @Param("keywords") String keywords);
}
