package com.central.backend.model.enums;

import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 角色代码
 */
public enum RoleEnum {
    SUPER_ADMIN("1", "超级管理员"),
    MERCHANT_ADMIN("2", "商户管理员"),
    SITE_ADMIN("3", "站点管理员");
    private final String status;
    private final String remark;

    RoleEnum(String status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }
    public static Map<String, String> getOptions() {
        Stream<RoleEnum> stream = Arrays.stream(values());
        return stream.collect(Collectors.toMap(o -> o.status, o -> {
            return o.remark;
        }));
    }
}
