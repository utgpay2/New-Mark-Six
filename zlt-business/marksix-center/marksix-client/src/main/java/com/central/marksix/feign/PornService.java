package com.central.marksix.feign;

import com.central.common.constant.ServiceNameConstants;
import com.central.marksix.feign.callback.PornServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 */
@FeignClient(name = ServiceNameConstants.PORN_SERVER,
        fallbackFactory = PornServiceFallbackFactory.class, decode404 = true)
public interface PornService {
}