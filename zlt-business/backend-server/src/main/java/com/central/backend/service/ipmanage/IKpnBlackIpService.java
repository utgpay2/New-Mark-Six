package com.central.backend.service.ipmanage;

import com.central.backend.model.vo.KpnBlackIpVO;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.KpnBlackIp;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
public interface IKpnBlackIpService extends ISuperService<KpnBlackIp> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<KpnBlackIpVO> findList(Map<String, Object> params, SysUser user);

    /**
     * IP黑名单检查
     * @param ip
     * @return
     */
    public Boolean ipcheck(String ip, SysUser user);

    Result saveOrUpdateKpnBlackIp(KpnBlackIp kpnBlackIp, SysUser user);

    Result deleteKpnBlackIp(@PathVariable Long id);
}

