//package com.central.porn.core.language;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Map;
//
//@Slf4j
//@Getter
//@Setter
//@Component
//@RefreshScope
//@ConfigurationProperties(prefix = "language")
//public class LanguageProps {
//    //英语
//    private Map<String, String> en;
//    //中文
//    private Map<String, String> zh;
//    //越南语
//    private Map<String, String> vi;
//    //高棉语
//    private Map<String, String> khm;
//    //泰语
//    private Map<String, String> th;
//
//
//    @PostConstruct
//    public void languageConfig() {
//        log.info("英文:{}", en);
//        log.info("中文:{}", zh);
//        log.info("高棉语:{}", khm);
//        log.info("越南语:{}", vi);
//        log.info("泰语:{}", th);
//    }
//
//    public Map<String, String> getLanguageMap(String language) {
//        switch (language) {
//            case "zh":
//                return getZh();
//            case "khm":
//                return getKhm();
//            case "th":
//                return getTh();
//            case "vi":
//                return getVi();
//            case "en":
//            default:
//                return getEn();
//        }
//    }
//
//}
