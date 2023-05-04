package com.central.common.model.enums;

/**
 * 正则表达式汇总
 */
public enum RegexEnum {

    NAME("^[\\u0391-\\uFFE5a-zA-Z·&\\\\s]{1,20}+$","姓名","长度限制1~20位,并且只能输入中英文"),
    PASSWORD("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$","密码","长度限制6~15位,并且必须是数字和字母的组合"),
    PHONE("^[0-9]{6,20}","手机号","长度限制6~20位,并且仅允许输入数字"),
    ACCOUNT("^[0-9a-zA-Z]{6,15}$","用户名","必须是6-15位的字母或数字"),
    CREATE_ACCOUNT("^[0-9a-zA-Z]{6,30}$","用户名","必须是6-30位的字母或数字"),
    PASSWORDAPP("^[0-9a-zA-Z]{6,15}$","密码","必须是6-15位的字母或数字"),
    BANK_ACCOUNT("^\\d{9,20}$","银行卡号","长度限制9~20位,并且仅允许输入数字"),
    EMAIL("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}","邮箱","邮箱格式填写错误"),
    WEBCHAT("^[a-zA-Z]([-_a-zA-Z0-9]{5,19})+$","微信号","仅支持6-20个字母、数字、下划线或减号，以字母开头"),
    NUMBER_OR_LETTER("^[0-9a-zA-Z]{1,20}$","手机号","长度限制1~20位,并且必须是数字或字母"),
    QQ("[1-9][0-9]{4,14}","QQ号","长度限制5~15位,仅允许输入数字且不能以0开头"),
    ;

    private String regex;

    private String name;

    private String desc;

    RegexEnum(String regex, String name, String desc) {
        this.regex = regex;
        this.name = name;
        this.desc = desc;
    }

    public String getRegex() {
        return regex;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
