package com.central.common.constant;

import com.central.common.language.LanguageFromEnum;

/**
 * 国际化Keys
 *
 * @author lance
 * @since 2022 -01-25 15:42:26
 */
public interface I18nKeys {

//    /**
//     * header 选择语言
//     */
//    String LANGUAGE = "language";

    /**
     * header 请求来源
     */
    String REQUEST_SOURCE = "requestSource";

    /**
     * header 前台请求
     */
    String FRONT = "front";

//    /**
//     * 前台PC
//     */
//    Integer FRONT_PC = 0;
//
//    /**
//     * 后台
//     */
//    Integer BACKEND = 1;
//
//    /**
//     * H5/APP
//     */
//    Integer FRONT_APP = 2;
//
//
//    /**
//     * 前台MESSAGE
//     */
//    Integer FRONT_MESSAGE = 3;
//
//    /**
//     * 后台MESSAGE
//     */
//    Integer BACKEND_MESSAGE = 4;

    interface Redis {
        // 前台PC
        interface FrontPc {
            /**
             * 中文国际化 key
             */
            String ZH_KEY = "i18n:source:zh:hash:" + LanguageFromEnum.FRONT_PC.getCode();
            /**
             * 英文国际化 key
             */
            String EN_KEY = "i18n:source:en:hash:" + LanguageFromEnum.FRONT_PC.getCode();

            /**
             * 高棉语国际化 key
             */
            String KH_KEY = "i18n:source:kh:hash:" + LanguageFromEnum.FRONT_PC.getCode();

            /**
             * 泰语国际化 key
             */
            String TH_KEY = "i18n:source:th:hash:" + LanguageFromEnum.FRONT_PC.getCode();

            /**
             * 越南语国际化 key
             */
            String VI_KEY = "i18n:source:vi:hash:" + LanguageFromEnum.FRONT_PC.getCode();
            /**
             * 马来语国际化 key
             */
            String MY_KEY = "i18n:source:my:hash:" + LanguageFromEnum.FRONT_PC.getCode();
        }

        // 前台移动端
        interface FrontApp {
            /**
             * 中文国际化 key
             */
            String ZH_KEY = "i18n:source:zh:hash:" + LanguageFromEnum.FRONT_APP.getCode();
            /**
             * 英文国际化 key
             */
            String EN_KEY = "i18n:source:en:hash:" + LanguageFromEnum.FRONT_APP.getCode();

            /**
             * 高棉语国际化 key
             */
            String KH_KEY = "i18n:source:kh:hash:" + LanguageFromEnum.FRONT_APP.getCode();

            /**
             * 泰语国际化 key
             */
            String TH_KEY = "i18n:source:th:hash:" + LanguageFromEnum.FRONT_APP.getCode();

            /**
             * 越南语国际化 key
             */
            String VI_KEY = "i18n:source:vi:hash:" + LanguageFromEnum.FRONT_APP.getCode();
            /**
             * 马来语国际化 key
             */
            String MY_KEY = "i18n:source:my:hash:" + LanguageFromEnum.FRONT_APP.getCode();
        }

        // 前台message
        interface FrontMessage{
            /**
             * 中文国际化 key
             */
            String ZH_KEY = "i18n:source:zh:hash:" + LanguageFromEnum.FRONT_MESSAGE.getCode();
            /**
             * 英文国际化 key
             */
            String EN_KEY = "i18n:source:en:hash:" + LanguageFromEnum.FRONT_MESSAGE.getCode();

            /**
             * 高棉语国际化 key
             */
            String KH_KEY = "i18n:source:kh:hash:" + LanguageFromEnum.FRONT_MESSAGE.getCode();

            /**
             * 泰语国际化 key
             */
            String TH_KEY = "i18n:source:th:hash:" + LanguageFromEnum.FRONT_MESSAGE.getCode();

            /**
             * 越南语国际化 key
             */
            String VI_KEY = "i18n:source:vi:hash:" + LanguageFromEnum.FRONT_MESSAGE.getCode();
            /**
             * 马来语国际化 key
             */
            String MY_KEY = "i18n:source:my:hash:" + LanguageFromEnum.FRONT_MESSAGE.getCode();
        }

        // 后台
        interface Backend {
            /**
             * 中文国际化 key
             */
            String ZH_KEY = "i18n:source:zh:hash:" + LanguageFromEnum.BACKEND.getCode();
            /**
             * 英文国际化 key
             */
            String EN_KEY = "i18n:source:en:hash:" + LanguageFromEnum.BACKEND.getCode();

            /**
             * 高棉语国际化 key
             */
            String KH_KEY = "i18n:source:kh:hash:" + LanguageFromEnum.BACKEND.getCode();

            /**
             * 泰语国际化 key
             */
            String TH_KEY = "i18n:source:th:hash:" + LanguageFromEnum.BACKEND.getCode();

            /**
             * 越南语国际化 key
             */
            String VI_KEY = "i18n:source:vi:hash:" + LanguageFromEnum.BACKEND.getCode();
            /**
             * 马来语国际化 key
             */
            String MY_KEY = "i18n:source:my:hash:" + LanguageFromEnum.BACKEND.getCode();
        }

        // 后台Message
        interface BackendMessage {
            /**
             * 中文国际化 key
             */
            String ZH_KEY = "i18n:source:zh:hash:" + LanguageFromEnum.BACKEND_MESSAGE.getCode();
            /**
             * 英文国际化 key
             */
            String EN_KEY = "i18n:source:en:hash:" + LanguageFromEnum.BACKEND_MESSAGE.getCode();

            /**
             * 高棉语国际化 key
             */
            String KH_KEY = "i18n:source:kh:hash:" + LanguageFromEnum.BACKEND_MESSAGE.getCode();

            /**
             * 泰语国际化 key
             */
            String TH_KEY = "i18n:source:th:hash:" + LanguageFromEnum.BACKEND_MESSAGE.getCode();

            /**
             * 越南语国际化 key
             */
            String VI_KEY = "i18n:source:vi:hash:" + LanguageFromEnum.BACKEND_MESSAGE.getCode();
            /**
             * 马来语国际化 key
             */
            String MY_KEY = "i18n:source:my:hash:" + LanguageFromEnum.BACKEND_MESSAGE.getCode();
        }
    }

}
