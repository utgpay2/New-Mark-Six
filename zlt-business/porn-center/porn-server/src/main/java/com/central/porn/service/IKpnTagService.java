package com.central.porn.service;

import com.central.common.model.KpnTag;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnTagVo;

import java.util.List;


public interface IKpnTagService extends ISuperService<KpnTag> {

    /**
     * 批量获取
     *
     * @param tagIds
     * @return
     */
    List<KpnTagVo> getByTagIds(List<Long> tagIds);
}
