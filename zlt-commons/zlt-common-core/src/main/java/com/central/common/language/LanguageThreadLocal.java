package com.central.common.language;


public class LanguageThreadLocal {

    private static final ThreadLocal<String> languageThreadLocal = ThreadLocal.withInitial(LanguageEnum.EN::getValue);

    public static void setLanguage(String language) {
        languageThreadLocal.set(LanguageEnum.getByValue(language).getValue());
    }

    public static String getLanguage() {
        return languageThreadLocal.get();
    }

}
