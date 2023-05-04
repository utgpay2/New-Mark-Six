package com.central.backend.service.ipmanage;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.SysWhiteIp;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:07:56
 */
public interface ISysWhiteIpService extends ISuperService<SysWhiteIp> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<SysWhiteIp> findList(Map<String, Object> params);
    Boolean ipcheck(String ip);
    Result saveOrUpdateSysWhiteIp(SysWhiteIp sysWhiteIp, SysUser user);
    Result deleteSysWhiteIp(@PathVariable Long id);
}

