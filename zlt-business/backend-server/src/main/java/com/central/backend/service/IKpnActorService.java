package com.central.backend.service;

import com.central.backend.model.vo.KpnActorVO;
import com.central.common.model.KpnActor;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 演员列表
 *
 * @author yixiu
 * @date 2023-02-03 16:31:09
 */
public interface IKpnActorService extends ISuperService<KpnActor> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<KpnActorVO> findList(Map<String, Object> params, SysUser user);
    Result deleteKpnActor(Long id);
    Result saveOrUpdateKpnActor(KpnActor kpnActor,SysUser user);
}

