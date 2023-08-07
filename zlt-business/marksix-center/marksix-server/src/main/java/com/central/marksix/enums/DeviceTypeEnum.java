package com.central.marksix.enums;

/**
 * 商户专题状态
 */
public enum DeviceTypeEnum {
    H5(1, "H5"),
    PC(2, "PC");
    private final Integer status;
    private final String remark;

    DeviceTypeEnum(Integer status, String remark) {
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
