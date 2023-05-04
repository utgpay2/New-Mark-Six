package com.central.common.annotation;

import java.lang.annotation.*;

/**
 * 请求的方法参数SysUser上添加该注解，则注入当前登录人信息
 * 例1：public void test(@LoginUser SysUser user) //只有username 和 roles
 *
 *
 * 移除 ---- 例2：public void test(@LoginUser(isFull = true) SysUser user) //能获取SysUser对象的所有信息
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
    // 删除有isFull去关联查询用户信息，查询用户信息应由用户自己去根据id查询，避免循环引用
//    /**
//     * 是否查询SysUser对象所有信息，true则通过rpc接口查询
//     */
//    boolean isFull() default false;
}
