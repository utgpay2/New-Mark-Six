package com.central.push;

import com.central.common.lb.annotation.EnableFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * 配置服
 */
@EnableDiscoveryClient
@EnableFeignInterceptor
@EnableFeignClients(basePackages = "com.central")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScans(value = {
        @ComponentScan(value = "com.central")
})
public class PushCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(PushCenterApp.class, args);
    }
}
