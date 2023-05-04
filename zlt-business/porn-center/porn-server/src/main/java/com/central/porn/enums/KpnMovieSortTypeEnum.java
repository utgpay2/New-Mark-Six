package com.central.porn.enums;

public enum KpnMovieSortTypeEnum {

    HOT("HOT", "最热-观影次数"),
    LATEST("LATEST", "最新-上架时间"),
    DURATION("DURATION", "时长-影片时长"),
    ;

    //排序类型
    private String type;
    //描述
    private String remark;

    KpnMovieSortTypeEnum(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public static boolean isLegalType(String type) {
        for (KpnMovieSortTypeEnum typeEnum : values()) {
            if(typeEnum.getType().equalsIgnoreCase(type)){
                return true;
            }
        }
        return false;
    }

    public String getType() {
        return type;
    }
}
