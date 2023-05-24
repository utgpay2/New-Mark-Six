package com.central.common.model.enums;

/**
 * M币账变记录相关枚举
 */
public enum MbChangeTypeEnum {
    BETTING(1, "投注", -1),
    CANCELBETTING(2, "撤销投注", 1)
//    SIGN_REWARD(2, "签到", 1),
    ;

    private final Integer type;

    private final String name;

    /**
     * 1.加钱，-1.减钱
     */
    private final Integer addOrSub;

    MbChangeTypeEnum(Integer type, String name, Integer addOrSub) {
        this.type = type;
        this.name = name;
        this.addOrSub = addOrSub;
    }

    private static MbChangeTypeEnum getMbChangeTypeEnumByType(Integer changeType) {
        for (MbChangeTypeEnum typeEnum : values()) {
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
        for (MbChangeTypeEnum value : values()) {
            if(value.type == type){
                return value.name;
            }
        }
        return null;
    }

}
