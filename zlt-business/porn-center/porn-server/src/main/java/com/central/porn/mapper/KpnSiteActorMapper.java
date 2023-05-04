package com.central.porn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.KpnSiteActor;
import com.central.porn.entity.vo.KpnActorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KpnSiteActorMapper extends BaseMapper<KpnSiteActor> {

    /**
     * 获取演员列表-按收藏量
     *
     * @param sid        站点id
     * @param sortOrder  排序顺序
     * @param startIndex 起始index
     * @param pageSize   每页条数
     * @return
     */
    List<Long> getActorListByFavorites(@Param("sid") Long sid, @Param("sortOrder") String sortOrder, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 获取演员列表-按创建时间
     *
     * @param sid       站点id
     * @param sortOrder 排序顺序
     * @param startIndex  当前页
     * @param pageSize  每页条数
     * @return
     */
    List<Long> getActorListByCreateTime(@Param("sid") Long sid, @Param("sortOrder") String sortOrder, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 站点演员总数
     * @param sid 站点id
     * @return
     */
    Long getActorCount(@Param("sid") Long sid);
}
