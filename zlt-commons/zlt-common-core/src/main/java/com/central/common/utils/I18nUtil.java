package com.central.common.utils;

import cn.hutool.core.util.StrUtil;
import com.central.common.constant.I18nKeys;
import com.central.common.constant.PornConstants;
import com.central.common.dto.I18nSourceDTO;
import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 国际化工具
 *
 * @author lance
 * @since 2022 -01-25 18:05:09
 */
@Component
public class I18nUtil implements ApplicationContextAware {

    private static RedisTemplate<String, String> redisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = applicationContext.getBean("stringRedisTemplate", RedisTemplate.class);
    }

    /**
     * 翻译
     *
     * @param languageValue 语言简写
     * @param key           待翻译文本
     * @return {@link String} 出参释义
     * @author lance
     * @since 2022 -01-25 18:18:28
     */
    public static String translate(String languageValue, String key, String requestSource) {
        if (LanguageEnum.ZH.getValue().equalsIgnoreCase(languageValue)) {
            return key;
        }
        if (null == redisTemplate) {
            return key;
        }
        if (StrUtil.isBlank(key)) {
            return key;
        }
        String value = redisTemplate.<String, String>opsForHash().get(keyOf(languageValue, requestSource), key);
        if (StringUtils.isBlank(value)) {
            return key;
        }
        return value;
    }

    /**
     * 翻译
     *
     * @param key 待翻译文本
     * @return {@link String} 出参释义
     * @author lance
     * @since 2022 -01-25 18:19:02
     */
    public static String t(String key) {
//        HttpServletRequest request = ServletUtil.getHttpServletRequest();
//        if (null == request) {
//            return key;
//        }
//        String language = request.getHeader(I18nKeys.LANGUAGE);
        String language = LanguageThreadLocal.getLanguage();
        String requestSource = null;//request.getHeader(I18nKeys.REQUEST_SOURCE);
        return translate(language, key, requestSource);
    }

    /**
     * 重设国际化资源redis缓存
     *
     * @param redisKey 入参释义
     * @param key 入参释义
     * @param value 入参释义
     * @author lance
     * @since 2022 -01-25 18:05:09
     */
    public static void resetSource(String redisKey, String key, String value) {
        redisTemplate.opsForHash().put(redisKey, key, value);
    }

    public static void deleteByKey(String redisKey, String key) {
        redisTemplate.opsForHash().delete(redisKey, key);
    }

    // 找到对应语言的redis key
    private static String keyOf(String languageValue, String requestSource) {
        if (StrUtil.isBlank(languageValue)) {
            return I18nKeys.Redis.BackendMessage.EN_KEY;
        }
        if (I18nKeys.FRONT.equals(requestSource)) {
            switch (LanguageEnum.getByValue(languageValue)) {
                case ZH:
                    return I18nKeys.Redis.FrontMessage.ZH_KEY;
                case KH:
                    return I18nKeys.Redis.FrontMessage.KH_KEY;
                case TH:
                    return I18nKeys.Redis.FrontMessage.TH_KEY;
                case VI:
                    return I18nKeys.Redis.FrontMessage.VI_KEY;
                case MY:
                    return I18nKeys.Redis.FrontMessage.MY_KEY;
                default:
                    return I18nKeys.Redis.FrontMessage.EN_KEY;
            }
        } else {
            switch (LanguageEnum.getByValue(languageValue)) {
                case ZH:
                    return I18nKeys.Redis.BackendMessage.ZH_KEY;
                case KH:
                    return I18nKeys.Redis.BackendMessage.KH_KEY;
                case TH:
                    return I18nKeys.Redis.BackendMessage.TH_KEY;
                case VI:
                    return I18nKeys.Redis.BackendMessage.VI_KEY;
                case MY:
                    return I18nKeys.Redis.BackendMessage.MY_KEY;
                default:
                    return I18nKeys.Redis.BackendMessage.EN_KEY;
            }
        }
    }

    /**
     * 获取后台所有语言的国际化资源
     *
     * @return {@link I18nSourceDTO} 出参释义
     * @author lance
     * @since 2022 -01-25 18:11:01
     */
    public static I18nSourceDTO getBackendFullSource() {
        I18nSourceDTO dto = new I18nSourceDTO();
        HashOperations<String, String, String> ops = redisTemplate.<String, String>opsForHash();
        dto.setZh(ops.entries(I18nKeys.Redis.Backend.ZH_KEY));
        dto.setEn(ops.entries(I18nKeys.Redis.Backend.EN_KEY));
        dto.setKh(ops.entries(I18nKeys.Redis.Backend.KH_KEY));
        dto.setTh(ops.entries(I18nKeys.Redis.Backend.TH_KEY));
        dto.setVi(ops.entries(I18nKeys.Redis.Backend.VI_KEY));
        dto.setMy(ops.entries(I18nKeys.Redis.Backend.MY_KEY));
        return dto;
    }

    /**
     * 获取前台PC所有语言的国际化资源
     *
     * @return {@link I18nSourceDTO} 出参释义
     * @author lance
     * @since 2022 -01-28 12:45:22
     */
    public static I18nSourceDTO getFrontFullSource() {
        I18nSourceDTO dto = new I18nSourceDTO();
        HashOperations<String, String, String> ops = redisTemplate.<String, String>opsForHash();
        dto.setZh(ops.entries(I18nKeys.Redis.FrontPc.ZH_KEY));
        dto.setEn(ops.entries(I18nKeys.Redis.FrontPc.EN_KEY));
        dto.setKh(ops.entries(I18nKeys.Redis.FrontPc.KH_KEY));
        dto.setTh(ops.entries(I18nKeys.Redis.FrontPc.TH_KEY));
        dto.setVi(ops.entries(I18nKeys.Redis.FrontPc.VI_KEY));
        dto.setMy(ops.entries(I18nKeys.Redis.FrontPc.MY_KEY));
        return dto;
    }

    /**
     * 获取前台移动端所有语言的国际化资源
     *
     * @return {@link I18nSourceDTO} 出参释义
     * @author lance
     * @since 2022 -01-28 12:45:22
     */
    public static I18nSourceDTO getFrontAppFullSource() {
        I18nSourceDTO dto = new I18nSourceDTO();
        HashOperations<String, String, String> ops = redisTemplate.<String, String>opsForHash();
        dto.setZh(ops.entries(I18nKeys.Redis.FrontApp.ZH_KEY));
        dto.setEn(ops.entries(I18nKeys.Redis.FrontApp.EN_KEY));
        dto.setKh(ops.entries(I18nKeys.Redis.FrontApp.KH_KEY));
        dto.setTh(ops.entries(I18nKeys.Redis.FrontApp.TH_KEY));
        dto.setVi(ops.entries(I18nKeys.Redis.FrontApp.VI_KEY));
        dto.setMy(ops.entries(I18nKeys.Redis.FrontApp.MY_KEY));
        return dto;
    }

    /**
     * 获取前台Message所有语言的国际化资源
     *
     * @return {@link I18nSourceDTO} 出参释义
     * @author lance
     * @since 2022 -01-28 12:45:22
     */
    public static I18nSourceDTO getFrontMessageFullSource() {
        I18nSourceDTO dto = new I18nSourceDTO();
        HashOperations<String, String, String> ops = redisTemplate.<String, String>opsForHash();
        dto.setZh(ops.entries(I18nKeys.Redis.FrontMessage.ZH_KEY));
        dto.setEn(ops.entries(I18nKeys.Redis.FrontMessage.EN_KEY));
        dto.setKh(ops.entries(I18nKeys.Redis.FrontMessage.KH_KEY));
        dto.setTh(ops.entries(I18nKeys.Redis.FrontMessage.TH_KEY));
        dto.setVi(ops.entries(I18nKeys.Redis.FrontMessage.VI_KEY));
        dto.setMy(ops.entries(I18nKeys.Redis.FrontMessage.MY_KEY));
        return dto;
    }

    public static String getBackendValue(String key) {
        HttpServletRequest request = ServletUtil.getHttpServletRequest();
        if (Objects.isNull(request)) {
            return key;
        }
//        String language = request.getHeader(I18nKeys.LANGUAGE);
        String language = request.getHeader(PornConstants.Str.LANGUAGE);

        if (StrUtil.isBlank(language) || LanguageEnum.ZH.getValue().equalsIgnoreCase(language) || Objects.isNull(redisTemplate) || StrUtil.isBlank(key)) {
            return key;
        }
        String value = redisTemplate.<String, String>opsForHash().get(getRedisKey(language), key);
        if (StringUtils.isBlank(value)) {
            return key;
        }
        return value;
    }

    // 找到对应语言的redis key
    private static String getRedisKey(String languageValue) {
        LanguageEnum languageEnum = LanguageEnum.getByValue(languageValue);
        switch (languageEnum) {
            case ZH:
                return I18nKeys.Redis.Backend.ZH_KEY;
            case EN:
                return I18nKeys.Redis.Backend.EN_KEY;
            case KH:
                return I18nKeys.Redis.Backend.KH_KEY;
            case TH:
                return I18nKeys.Redis.Backend.TH_KEY;
            case VI:
                return I18nKeys.Redis.Backend.VI_KEY;
            case MY:
                return I18nKeys.Redis.Backend.MY_KEY;
        }
        return I18nKeys.Redis.Backend.EN_KEY;
    }
}
