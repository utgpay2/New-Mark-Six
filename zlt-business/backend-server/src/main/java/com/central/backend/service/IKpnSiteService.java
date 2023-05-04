package com.central.backend.service;

import com.central.backend.co.KpnSiteCo;
import com.central.backend.co.KpnSiteUpdateCo;
import com.central.backend.vo.KpnSiteListVo;
import com.central.backend.vo.KpnSiteVo;
import com.central.common.model.KpnSite;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/3
 */
public interface IKpnSiteService extends ISuperService<KpnSite> {

    PageResult<KpnSite> findSiteList( KpnSiteCo params);

    List<KpnSite> findKpnSiteList(Map<String, Object> params);

    KpnSiteVo findSiteTotal();


    Result saveOrUpdateSite( KpnSite kpnSite);


    Result updateEnabledSite(KpnSiteUpdateCo params);

    String getStringRandom(int length) ;


    List<KpnSiteListVo> findSiteBoxList(Integer roleId);

    /**
     * 获取站点列表
     * @return
     */
    List<KpnSite> getList();


}
