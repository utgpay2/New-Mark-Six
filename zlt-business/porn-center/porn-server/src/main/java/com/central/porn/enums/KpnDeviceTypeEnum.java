package com.central.porn.enums;

/**
 * 站点专题状态
 */
public enum KpnDeviceTypeEnum {
    H5(1, "H5"),
    PC(2, "PC");
    private final Integer status;
    private final String remark;

    KpnDeviceTypeEnum(Integer status, String remark) {
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
