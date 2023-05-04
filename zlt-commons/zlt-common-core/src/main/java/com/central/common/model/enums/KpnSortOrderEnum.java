package com.central.common.model.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * 通用排序规则
 */
public enum KpnSortOrderEnum {
    ASC(0, "正序"),
    DESC(1, "倒序"),
    ;

    //排序类型
    private Integer code;
    //描述
    private String remark;

    KpnSortOrderEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public static KpnSortOrderEnum getByCode(Integer code) {
        for (KpnSortOrderEnum orderEnum : values()) {
            if (orderEnum.getCode().equals(code)) {
                return orderEnum;
            }
        }
        return ASC;
    }

    public static boolean isLegalCode(Integer code) {
        for (KpnSortOrderEnum orderEnum : values()) {
            if (orderEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAsc(Integer code) {
        return ObjectUtil.isEmpty(code) || ASC.code.equals(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
