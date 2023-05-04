package com.central.user.annotation;

import com.central.user.feign.callback.MenuServiceFallbackFactory;
import com.central.user.feign.callback.RoleServiceFallbackFactory;
import com.central.user.feign.callback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启用户客户端
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({UserServiceFallbackFactory.class, MenuServiceFallbackFactory.class, RoleServiceFallbackFactory.class})
@EnableFeignClients(basePackages = "com.central")
public @interface EnableUserClient {

}
