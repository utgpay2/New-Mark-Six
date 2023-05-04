package com.central.common.model.enums;

/**
 * K币账变记录相关枚举
 */
public enum KbChangeTypeEnum {
    OPEN_VIP(1, "开i通vp", -1),
    SIGN_REWARD(2, "签到", 1),
    FILL_INVITE_CODE(3, "填写邀请码", 1),
    PROMOTION(4, "推广", 1),
    ;

    private final Integer type;

    private final String name;

    /**
     * 1.加钱，-1.减钱
     */
    private final Integer addOrSub;

    KbChangeTypeEnum(Integer type, String name, Integer addOrSub) {
        this.type = type;
        this.name = name;
        this.addOrSub = addOrSub;
    }

    private static KbChangeTypeEnum getKbChangeTypeEnumByType(Integer changeType) {
        for (KbChangeTypeEnum typeEnum : values()) {
            if (typeEnum.getType().equals(changeType)) {
                return typeEnum;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAddOrSub() {
        return addOrSub;
    }


    public static String getTypeName(Integer type) {
        for (KbChangeTypeEnum value : values()) {
            if(value.type == type){
                return value.name;
            }
        }
        return null;
    }

}
