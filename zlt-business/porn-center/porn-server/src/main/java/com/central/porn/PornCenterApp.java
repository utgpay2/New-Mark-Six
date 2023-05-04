package com.central.porn;

import com.central.common.lb.annotation.EnableFeignInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 配置服
 */
@Slf4j
@EnableDiscoveryClient
@EnableFeignInterceptor
@EnableScheduling
@EnableFeignClients(basePackages = "com.central")
@SpringBootApplication
@ComponentScans(value = {
        @ComponentScan(value = "com.central")
})
public class PornCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(PornCenterApp.class, args);
    }
}
