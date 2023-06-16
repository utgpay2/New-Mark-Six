package com.central.marksix.utils;

import java.io.UnsupportedEncodingException;

public class ByteUtils {
    private static final String CHARSET = "utf-8";
    private static final String FLAG_HEX = "0";
    private static final String FLAG_BYTE = "#";

    public static byte[] getBytes(String k) {
        if (k == null || k.length() == 0) {
            throw new IllegalArgumentException("ByteUtils: Key must not be empty");
        }
        try {
            return k.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String byte2hex(byte[] bytes) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = Integer.toHexString(bytes[n] & 0xFF);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    public static byte[] hex2byte(String str) {
        byte[] ret = new byte[str.length() / 2];
        try {
            for (int i = 0; i < ret.length; i++) {
                ret[i] = Integer.decode("#" + str.substring(2 * i, 2 * i + 2)).byteValue();
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return ret;
    }
}



