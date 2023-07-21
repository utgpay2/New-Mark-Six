package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.SiteCo;
import com.central.backend.vo.SiteListVo;
import com.central.backend.vo.SiteVo;
import com.central.common.model.Site;
import com.central.common.model.SysUser;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
 * @Author: Lulu
 * @Date: 2023/2/3
 */
@Mapper
public interface SiteMapper extends SuperMapper<Site> {

    List<Site> findList(Page<Site> page, @Param("r") SiteCo params);

    List<Site> findKpnSiteList(@Param("p") Map<String, Object> params);


    SiteVo findSiteTotal();


    List<SiteListVo>  findSiteBoxList(Integer roleId);

    SysUser getStationOwner(@Param("siteId")Integer siteId);
}
