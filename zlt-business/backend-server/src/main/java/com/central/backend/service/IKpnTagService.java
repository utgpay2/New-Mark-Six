package com.central.backend.service;

import com.central.backend.model.vo.KpnTagVO;
import com.central.backend.vo.CategoryVo;
import com.central.backend.vo.KpnTagVo;
import com.central.common.model.KpnTag;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 影片标签
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
public interface IKpnTagService extends ISuperService<KpnTag> {
    /**
     * 列表
     *
     * @param params
     * @return
     */
    PageResult<KpnTagVO> findList(Map<String, Object> params, SysUser user);

    List<KpnTagVo> findTagList( Map<String, Object> params);

    List<CategoryVo> findTagCategoryList( ) ;

    public Result removeKpnTag(Long id);
    Result saveOrUpdateKpnTag(KpnTag kpnTag, SysUser user);
}

