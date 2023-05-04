package com.central.common.model.enums;

import cn.hutool.core.util.ObjectUtil;
import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum KpnMovieSubtitleEnum {
    All(-1, "全部", "All", "ទាំងអស់។"),
    Nothing(0, "无字幕", "No subtitle", "គ្មានចំណងជើងរងទេ។"),
    Chinese(1, "中文字幕", "Chinese subtitle", "អត្ថបទរឿងចិន"),
    English(2, "英文字幕", "English subtitles", "អត្ថបទរឿងជាភាសាអង់គ្លេស"),
    Chinese_English(3, "中英文字幕", "Chinese and English subtitles", "អត្ថបទរឿងចិន និងអង់គ្លេស"),
    //    Cambodian("柬文字幕", "Khmer subtitles", "អត្ថបទរឿងខ្មែរ"),
    Other(4, "其他字幕", "Other subtitles", "ចំណងជើងរងផ្សេងទៀត។"),
    ;

    private Integer code;
    private String nameZh;
    private String nameEn;
    private String nameKh;

    KpnMovieSubtitleEnum(Integer code, String nameZh, String nameEn, String nameKh) {
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

        Stream<KpnMovieSubtitleEnum> stream = Arrays.stream(values());
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
