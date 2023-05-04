package com.central.porn.enums;

public enum KpnActorSortTypeEnum {

    HOT("HOT", "最热-收藏次数"),
    LATEST("LATEST", "最新-创建时间"),
    ;

    //排序类型
    private String type;
    //描述
    private String remark;

    KpnActorSortTypeEnum(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public static boolean isLegalType(String type) {
        for (KpnActorSortTypeEnum typeEnum : values()) {
            if (typeEnum.getType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    public String getType() {
        return type;
    }
}
