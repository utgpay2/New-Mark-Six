package com.central.common.model.enums;

/**
 * vip变动类型
 */
public enum VipChangeTypeEnum {
    SIGN(1, "签到", "签到累计达到:{}天,获取vip天数:{}"),
    FILL_INVITE_CODE(2, "填写邀请码", "填写邀请码:{},获取vip天数:{}"),
    PROMOTION(3, "推广", "推广会员id:{},获取vip天数:{}"),
    KB(4, "K币兑换", "消费K币:{},获取vip天数:{}"),
    CASH(5, "购买", "消费金额:{},币种:{},获取vip天数:{}");

    private int code;
    private String remark;
    private String logFormat;

    VipChangeTypeEnum(int code, String remark, String logFormat) {
        this.code = code;
        this.remark = remark;
        this.logFormat = logFormat;
    }

    public static String getLogFormatByCode(Integer code) {
        for (VipChangeTypeEnum value : values()) {
            if(value.code == code){
                return value.getLogFormat();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }

    public String getLogFormat() {
        return logFormat;
    }
}
