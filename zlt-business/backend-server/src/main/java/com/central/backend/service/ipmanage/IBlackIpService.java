package com.central.backend.service.ipmanage;

import com.central.backend.model.vo.BlackIpVO;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.BlackIp;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
public interface IBlackIpService extends ISuperService<BlackIp> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<BlackIpVO> findList(Map<String, Object> params, SysUser user);

    /**
     * IP黑名单检查
     * @param ip
     * @return
     */
    public Boolean ipcheck(String ip, SysUser user);

    Result saveOrUpdateBlackIp(BlackIp blackIp, SysUser user);

    Result deleteBlackIp(@PathVariable Long id);
}

