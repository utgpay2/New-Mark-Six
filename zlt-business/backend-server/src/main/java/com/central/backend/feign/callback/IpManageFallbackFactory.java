package com.central.backend.feign.callback;

import com.central.backend.feign.IpManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
public class IpManageFallbackFactory implements FallbackFactory<IpManageService> {
    @Override
    public IpManageService create(Throwable throwable) {
        /**
         * TODO 等待验证所有FallbackFactory是否完成
         */
        return new IpManageService() {
            @Override
            public Boolean ipWhitecheck(@PathVariable String ip){
                log.error("服务器异常，ipcheckIP白名单检查异常:{}", ip);
                return null;
            }

            @Override
            public Boolean ipBlackcheck(@PathVariable String ip){
                log.error("服务器异常，ipcheckIP黑名单单检查异常:{}", ip);
                return null;
            }

        };
    }
}
