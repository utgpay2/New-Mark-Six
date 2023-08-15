package com.proxy.center.util;



public class SecureToken {
    public static String generate() {
        String origin = RandomUtils.randomString();
        return MD5.encrypt(origin);
    }

    public static String sourceToken(String source, String token) {
        return source + "-" + token;
    }
}



