package com.proxy.center.feign;

import com.proxy.center.feign.callback.IpManageFallbackFactory;
import com.central.common.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author zlt
 */
@FeignClient(name = ServiceNameConstants.BACKEND_SERVICE, fallbackFactory = IpManageFallbackFactory.class, decode404 = true)
public interface IpManageService {
    /**
     * IP白名单检查
     * 查询用户实体对象SysUser
     *
     * @param ip
     * @return
     */
    @GetMapping(value = "/syswhiteip/ipcheck")
    Boolean ipWhitecheck(@PathVariable String ip);

    /**
     * IP黑名单检查
     * @param ip
     * @return
     */
    @GetMapping(value = "/kpnblackip/ipcheck")
    Boolean ipBlackcheck(@PathVariable String ip);
}
