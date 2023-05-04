package com.central.porn.feign.callback;

import com.central.porn.feign.PornService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * 降级工场
 */
@Slf4j
public class PornServiceFallbackFactory implements FallbackFactory<PornService> {

    @Override
    public PornService create(Throwable cause) {
        return new PornService() {

        };
    }
}
