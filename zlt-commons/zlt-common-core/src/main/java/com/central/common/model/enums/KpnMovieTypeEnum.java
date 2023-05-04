package com.central.common.model.enums;


import cn.hutool.core.util.ObjectUtil;
import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum KpnMovieTypeEnum {
    All(-1, "全部", "All", "ទាំងអស់។"),
    NO_Occlusion(0, "无码", "no occlusion", "គ្មានការបិទបាំងទេ។"),
    Covered(1, "有码", "Covered", "គ្របដណ្តប់"),
    ;

    private Integer code;
    private String nameZh;
    private String nameEn;
    private String nameKh;

    KpnMovieTypeEnum(Integer code, String nameZh, String nameEn, String nameKh) {
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

        Stream<KpnMovieTypeEnum> stream = Arrays.stream(values());
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
