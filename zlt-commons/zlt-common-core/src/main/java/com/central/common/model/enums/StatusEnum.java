package com.central.common.model.enums;

/**
 * 商户专题状态
 */
public enum StatusEnum {
    ZERO_FALSE(0, "否"),
    ONE_TRUE(1, "是");
    private final Integer status;
    private final String remark;

    StatusEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }
}
