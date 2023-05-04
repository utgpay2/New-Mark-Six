package com.central.common.language;

public enum LanguageFromEnum {
    FRONT_PC(0, "前端PC"),
    BACKEND(1, "后台"),
    FRONT_APP(2, "前端H5/APP"),
    FRONT_MESSAGE(3, "前台MESSAGE"),
    BACKEND_MESSAGE(4, "后台MESSAGE"),
    ;

    private Integer code;
    private String remark;

    LanguageFromEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public Integer getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
