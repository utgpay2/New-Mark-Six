package com.central.marksix.enums;

public enum ActorSortTypeEnum {

    HOT("HOT", "最热-收藏次数"),
    LATEST("LATEST", "最新-创建时间"),
    ;

    //排序类型
    private String type;
    //描述
    private String remark;

    ActorSortTypeEnum(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public static boolean isLegalType(String type) {
        for (ActorSortTypeEnum typeEnum : values()) {
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
