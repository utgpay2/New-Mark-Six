package com.central.common.model.enums;

/**
 * 会员注册类型
 */
public enum UserRegTypeEnum {
    SELF_REG(0,"注册"),
    ADMIN_CREATE(1,"人工创建"),;

    private Integer type;
    private String remark;

    UserRegTypeEnum(Integer type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }
}
