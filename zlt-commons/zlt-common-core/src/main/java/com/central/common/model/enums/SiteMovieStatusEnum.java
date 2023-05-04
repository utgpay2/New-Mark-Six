package com.central.common.model.enums;

import cn.hutool.core.util.ObjectUtil;

public enum SiteMovieStatusEnum {
    All(-1, "全部"),
    TO_PUBLISH(0, "待发布"),
    ON_SHELF(1, "上架"),
    OFF_SHELF(2, "下架"),
    ;

    private final Integer status;
    private final String remark;

    SiteMovieStatusEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public static boolean isAll(Integer status) {
        return ObjectUtil.isEmpty(status) || All.status.equals(status);
    }
}
