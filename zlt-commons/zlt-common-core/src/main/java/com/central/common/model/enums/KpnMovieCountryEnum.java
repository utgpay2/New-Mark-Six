package com.central.common.model.enums;


import cn.hutool.core.util.ObjectUtil;
import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum KpnMovieCountryEnum {
    All(-1,"全部","All","ទាំងអស់។"),
    Japan(0, "日本", "Japan", "ជប៉ុន"),
    China(1, "中国大陆", "China", "ចិន"),
    Taiwan(2, "中国台湾", "Taiwan", "តៃវ៉ាន់"),
    Korea(3, "韩国", "Korea", "កូរ៉េ"),
    EuropeanAmerican(4, "欧美", "European and american", "អឺរ៉ុប និងអាមេរិក"),
    SoutheastAsia(5, "东南亚", "Southeast Asia", "អាស៊ី\u200Bអា\u200Bគ្នេ\u200Bយ៏"),
    OtherAreas(6, "其他地区", "Other Areas", "តំបន់ផ្សេងទៀត។"),
    ;

    private Integer code;
    private String nameZh;
    private String nameEn;
    private String nameKh;

    KpnMovieCountryEnum(Integer code, String nameZh, String nameEn, String nameKh) {
        this.code = code;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.nameKh = nameKh;
    }

    public static Map<Integer, String> getOptions() {
        return getOptions(Boolean.FALSE);
    }

    public static Map<Integer, String> getOptions(Boolean needAll) {
        final String language = LanguageThreadLocal.getLanguage();

        Stream<KpnMovieCountryEnum> stream = Arrays.stream(values());
        if (!needAll) {
            stream = stream.filter(e -> e.code != -1);
        }

        return stream.collect(Collectors.toMap(o -> o.code, o -> {
            if (language.equalsIgnoreCase(LanguageEnum.ZH.name().toLowerCase())) {
                return o.nameZh;
            }

            if (language.equalsIgnoreCase(LanguageEnum.EN.name().toLowerCase())) {
                return o.nameEn;
            }

            if (language.equalsIgnoreCase(LanguageEnum.KH.name().toLowerCase())) {
                return o.nameKh;
            }
            return o.nameEn;
        }));
    }

    public static boolean isAll(Integer code) {
        return ObjectUtil.isEmpty(code) || All.code.equals(code);
    }
}
