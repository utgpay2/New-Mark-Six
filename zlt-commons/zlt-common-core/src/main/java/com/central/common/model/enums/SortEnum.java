package com.central.common.model.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * 通用排序规则
 */
public enum SortEnum {
    ASC(1, "正序"),
    DESC(2, "倒序"),
    ;

    //排序类型
    private Integer code;
    //描述
    private String remark;

    SortEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public static SortEnum getByCode(Integer code) {
        for (SortEnum orderEnum : values()) {
            if (orderEnum.getCode().equals(code)) {
                return orderEnum;
            }
        }
        return ASC;
    }

    public static boolean isLegalCode(Integer code) {
        for (SortEnum orderEnum : values()) {
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
