package com.central.common.model.enums;

public enum CurrencyEnum {
    USD("$","USD","美元"),
    CNY("¥","CNY","人民币"),
    THB("฿","THB","泰铢"),
    MYR("RM","MYR","马来币"),
    KHR("៛","KHR","柬埔寨币"),
    VND("₫","VND","越南盾"),;

    private final String symbol;
    private final String code;
    private final String remark;

    CurrencyEnum(String symbol,String code, String remark) {
        this.symbol = symbol;
        this.code = code;
        this.remark = remark;
    }

    public static String getSymbolByCode(String code){
        for (CurrencyEnum e : values()) {
            if(e.getCode().equalsIgnoreCase(code)){
                return e.getSymbol();
            }
        }
        return USD.symbol;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRemark() {
        return remark;
    }
}
