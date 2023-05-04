package com.central.user.util;


import java.util.Random;

/**
 * 生成随机密码
 */
public class PasswordUtil {

    public static String getRandomPwd(){

        Random rd = new Random();
        int rn= rd.nextInt(12 - 6 +1) + 6;
        String n = "";
        int getNum;
        int getNum1;
        do {
            getNum = Math.abs(rd.nextInt()) % 10 + 48;// 产生数字0-9的随机数
            getNum1 = Math.abs(rd.nextInt()) % 26 + 97;//产生字母a-z的随机数
            char num1 = (char) getNum;
            char num2 = (char) getNum1;
            String dn = Character.toString(num1);
            String dn1 = Character.toString(num2);
            if(Math.random()>0.5){
                n += dn;
            }else{
                n += dn1;
            }
        } while (n.length() < rn);

        return n;
    }


    public static String getWithdrawPassword(){

        Random rd = new Random();
        int rn= rd.nextInt(1) + 6;
        String n = "";
        int getNum;
        int getNum1;
        do {
            getNum = Math.abs(rd.nextInt()) % 10 + 48;// 产生数字0-9的随机数
            char num1 = (char) getNum;
            String dn = Character.toString(num1);
            if(Math.random()>0.5){
                n += dn;
            }
        } while (n.length() < rn);

        return n;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String randomPwd = getWithdrawPassword();
            System.out.println(randomPwd);
        }
    }
}
