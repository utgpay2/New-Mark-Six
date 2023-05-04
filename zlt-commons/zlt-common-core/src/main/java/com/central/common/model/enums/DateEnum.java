package com.central.common.model.enums;

/**
 * 站点专题状态
 */
public enum DateEnum {
    TODAY(1, "今日"),
    YESTERDAY(2, "昨日"),
    THISMONTH(3, "本月");
    private final Integer status;
    private final String remark;

    DateEnum(Integer status, String remark) {
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
