package com.central.marksix.utils;



import java.security.MessageDigest;

public class MD5 {
    public static String encrypt(String origin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return ByteUtils.byte2hex(md.digest(origin.getBytes())).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String encrypt16(String origin) {
        return encrypt(origin).substring(2, 18);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("12345qwertt"));;
    }
}



