package com.central.user;

import com.central.common.lb.annotation.EnableFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

/**
 * 用户中心
 */
@EnableDiscoveryClient
@EnableFeignInterceptor
@EnableFeignClients(basePackages = "com.central")
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
@ComponentScans(value = {
        @ComponentScan(value = "com.central")
})
public class UserCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApp.class, args);
    }
}
