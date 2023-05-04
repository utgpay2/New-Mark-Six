package com.central.common.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * 谷歌身份认证工具
 */
public class GoogleAuthUtil {

    /**
     * 生成一个随机秘钥
     * @return
     */
    public static String generateSecretKey() {
        GoogleAuthenticator gAuth  = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth .createCredentials();
        String keyStr = key.getKey();
        return keyStr;
    }

    public static String getQcode(String email, String secret) {
        String url = "https://chart.googleapis.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/"+ email + "%3Fsecret%3D" + secret;
        return url;
    }

    public static boolean check_code(String secret, int code) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isCodeValid = gAuth.authorize(secret, code);
        return isCodeValid;
    }

    public static void main(String[] args) {
        String key=GoogleAuthUtil.generateSecretKey();
        System.out.println(key);
        String qrcodeUrl = GoogleAuthUtil.getQcode("3392603362@qq.com", key);
        System.out.println(qrcodeUrl);
    }
}
