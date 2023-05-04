package com.central.porn.enums;

import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

/**
 * K币账变记录相关枚举
 */
public enum KbChangeTypeExtendEnum {
    OPEN_VIP(1, "开通vip", null, null, -1, "支出", "expenses", "ការចំណាយ"),
    SIGN_REWARD(2, "签到", "check in", "កត់\u200Bឈ្មោះ\u200Bចូល", 1, "收入", "income", "ប្រាក់ចំណូល"),
    FILL_INVITE_CODE(3, "填写邀请码", "fill invite code", "បំពេញលេខកូដអញ្ជើញ", 1, "收入", "income", "ប្រាក់ចំណូល"),
    PROMOTION(4, "推广", "to promote", "ដើម្បីលើកកម្ពស់", 1, "收入", "income", "ប្រាក់ចំណូល"),
    ;

    private final Integer type;

    private final String name;
    private final String nameEn;
    private final String nameKh;

    /**
     * 1.加钱，-1.减钱
     */
    private final Integer addOrSub;
    private final String addOrSubNameZh;
    private final String addOrSubNameEn;
    private final String addOrSubNameKh;

    KbChangeTypeExtendEnum(Integer type, String name, String nameEn, String nameKh, Integer addOrSub, String addOrSubNameZh, String addOrSubNameEn, String addOrSubNameKh) {
        this.type = type;
        this.name = name;
        this.nameEn = nameEn;
        this.nameKh = nameKh;
        this.addOrSub = addOrSub;
        this.addOrSubNameZh = addOrSubNameZh;
        this.addOrSubNameEn = addOrSubNameEn;
        this.addOrSubNameKh = addOrSubNameKh;
    }

    /**
     * 是否支出
     *
     * @return
     */
    public static Boolean isSub(Integer changeType) {
        return !getKbChangeTypeEnumByType(changeType).getType().equals(KbChangeTypeExtendEnum.OPEN_VIP.getType());
    }

    public static KbChangeTypeExtendEnum getKbChangeTypeEnumByType(Integer changeType) {
        for (KbChangeTypeExtendEnum typeEnum : values()) {
            if (typeEnum.getType().equals(changeType)) {
                return typeEnum;
            }
        }
        return null;
    }

    public static String getLanguageNameByType(Integer changeType) {
        KbChangeTypeExtendEnum kbChangeTypeExtendEnum = getKbChangeTypeEnumByType(changeType);
        if (kbChangeTypeExtendEnum == null) {
            return kbChangeTypeExtendEnum.getNameEn();
        }

        final String language = LanguageThreadLocal.getLanguage();
        if (language.equalsIgnoreCase(LanguageEnum.ZH.name().toLowerCase())) {
            return kbChangeTypeExtendEnum.getName();
        }
        else if (language.equalsIgnoreCase(LanguageEnum.EN.name().toLowerCase())) {
            return kbChangeTypeExtendEnum.getNameEn();
        }
        else if (language.equalsIgnoreCase(LanguageEnum.KH.name().toLowerCase())) {
            return kbChangeTypeExtendEnum.getNameKh();
        }

        return kbChangeTypeExtendEnum.getNameEn();
    }

    public static String getLanguageAddOrSubNameByType(Integer changeType) {
        KbChangeTypeExtendEnum kbChangeTypeExtendEnum = getKbChangeTypeEnumByType(changeType);
        if (kbChangeTypeExtendEnum == null) {
            return kbChangeTypeExtendEnum.getNameEn();
        }

        final String language = LanguageThreadLocal.getLanguage();
        if (language.equalsIgnoreCase(LanguageEnum.ZH.name().toLowerCase())) {
            return kbChangeTypeExtendEnum.getAddOrSubNameZh();
        }

        if (language.equalsIgnoreCase(LanguageEnum.EN.name().toLowerCase())) {
            return kbChangeTypeExtendEnum.getAddOrSubNameEn();
        }

        if (language.equalsIgnoreCase(LanguageEnum.KH.name().toLowerCase())) {
            return kbChangeTypeExtendEnum.getAddOrSubNameKh();
        }

        return kbChangeTypeExtendEnum.getNameEn();
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameKh() {
        return nameKh;
    }

    public String getAddOrSubNameZh() {
        return addOrSubNameZh;
    }

    public String getAddOrSubNameEn() {
        return addOrSubNameEn;
    }

    public String getAddOrSubNameKh() {
        return addOrSubNameKh;
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
        for (KbChangeTypeExtendEnum value : values()) {
            if (value.type == type) {
                return value.name;
            }
        }
        return null;
    }

}
