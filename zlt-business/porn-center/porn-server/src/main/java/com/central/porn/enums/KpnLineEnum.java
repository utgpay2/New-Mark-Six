package com.central.porn.enums;

import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

/**
 * 线路
 */
public enum KpnLineEnum {
    LINE_1(1, "线路1", "line 1", "ជួរទី 1"),
    LINE_2(2, "线路2", "line 2", "ជួរទី 2"),
    LINE_3(3, "线路3", "line 3", "ជួរទី 3"),
    ;

    private Integer line;
    private String nameZh;
    private String nameEn;
    private String nameKh;

    KpnLineEnum(Integer line, String nameZh, String nameEn, String nameKh) {
        this.line = line;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.nameKh = nameKh;
    }

    public static String getNameByLine(Integer line) {
        KpnLineEnum lineEnum = null;
        for (KpnLineEnum e : values()) {
            if (e.line.equals(line)) {
                lineEnum = e;
                break;
            }
        }
        if (lineEnum == null) {
            return lineEnum.getNameEn();
        }

        final String language = LanguageThreadLocal.getLanguage();
        if (language.equalsIgnoreCase(LanguageEnum.ZH.name().toLowerCase())) {
            return lineEnum.getNameZh();
        }

        if (language.equalsIgnoreCase(LanguageEnum.EN.name().toLowerCase())) {
            return lineEnum.getNameEn();
        }

        if (language.equalsIgnoreCase(LanguageEnum.KH.name().toLowerCase())) {
            return lineEnum.getNameKh();
        }

        return lineEnum.getNameEn();
    }

    public Integer getLine() {
        return line;
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
