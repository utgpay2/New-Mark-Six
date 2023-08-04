package com.central.common.model.enums;

/**
 * M币账变记录相关枚举
 */
public enum MbChangeTypeEnum {
    BETTING(1, "投注", -1),
    CANCELBETTING(2, "撤销投注", 1),
    BETTINGWIN(3, "赢", 1),
    BETTINGTIE(4, "和", 1),
    WITHDRAWAL(5, "下分", -1),
    RECHARGE(6, "上分", 1),
    USER_WITHDRAWAL(7, "上级上分", 1),
    USER_RECHARGE(8, "上级下分", -1),
    STATION_OWNER_RECHARGE(10, "商户正式授信", 1),
    STATION_OWNER_RECHARGE_TEST(11, "商户测试授信", 1),
    PROXY_WITHDRAWAL(12, "代理收回正式授信额度", -1),
    PROXY_WITHDRAWAL_TEST(13, "代理收回测试授信额度", -1),
    PROXY_RECHARGE(14, "代理给与正式授信额度",1),
    PROXY_RECHARGE_TEST(15, "代理给与测试授信额度",1),
    OWNER_WITHDRAWAL(16, "收回正式授信额度", 1),
    OWNER_WITHDRAWAL_TEST(17, "收回测试授信额度", 1),
    OWNER_RECHARGE(18, "给与正式授信额度",-1),
    OWNER_RECHARGE_TEST(19, "给与测试授信额度",-1)
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

    public static MbChangeTypeEnum getMbChangeTypeEnumByType(Integer changeType) {
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
