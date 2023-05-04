package com.central.porn.enums;

import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

public enum OrderStatusEnum {
    approval(0, "待审核", "Waiting for approval", "កំពុងរង់ចាំការយល់ព្រម"),
    passed(1, "通过", "pass", "ឆ្លងកាត់"),
    reject(2, "拒绝", "reject", "បដិសេធ"),
    ;
    private final Integer code;
    private final String nameZh;
    private final String nameEn;
    private final String nameKh;

    OrderStatusEnum(Integer code, String nameZh, String nameEn, String nameKh) {
        this.code = code;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.nameKh = nameKh;
    }

    public static String getNameByCode(Integer code) {
        for (OrderStatusEnum e : values()) {
            if (e.code.equals(code)) {
                final String language = LanguageThreadLocal.getLanguage();
                if (language.equalsIgnoreCase(LanguageEnum.ZH.name().toLowerCase())) {
                    return e.getNameZh();
                }else if(language.equalsIgnoreCase(LanguageEnum.EN.name().toLowerCase())){
                    return e.getNameEn();
                }else if(language.equalsIgnoreCase(LanguageEnum.KH.name().toLowerCase())){
                    return e.getNameKh();
                }
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getNameZh() {
        return nameZh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameKh() {
        return nameKh;
    }
}
