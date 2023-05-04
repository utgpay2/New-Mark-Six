package com.central.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class EncryptUtil {
    private EncryptUtil(){
    }

    private static final int SALT_LENGTH = 6;
    private static final String SEPARATOR = "#";

    public static String aesDecrypt(String key, String encrypted){

        try {
            IvParameterSpec iv = generalAesIVSpec(key);

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(bytes, StandardCharsets.UTF_8);

        }catch (Exception ex){
            log.info("decrypt error aesKey:" + key, ex);
        }
        return null;
    }

    private static IvParameterSpec generalAesIVSpec(String key) {
        SecureRandom random = new SecureRandom();
        byte[] bytesIV = new byte[16];
        random.nextBytes(bytesIV);
        bytesIV = key.getBytes(StandardCharsets.UTF_8);
        return new IvParameterSpec(bytesIV);
    }

    /**
     * 进行MD5加密
     *
     * @param dataStr
     * @return
     */
    public static String md5(String dataStr) {
        String result = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(dataStr.getBytes(StandardCharsets.UTF_8));
            byte[] b = md.digest();
            int i;
            StringBuffer sb = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i < 0){
                    i += 256;
                }
                if(i < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            result = sb.toString();

        }catch (Exception e){

        }
        return result;
    }

    public enum EncryptionType {
        MD5("md5"),
        SHA256("sha256");

        private String type;

        EncryptionType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public static EncryptionType getEncryptionType(String type) {
            if (StringUtils.isEmpty(type)) {
                return MD5;
            }
            for (EncryptionType encryptionType : EncryptionType.values()) {
                if (encryptionType.type.equalsIgnoreCase(type)) {
                    return encryptionType;
                }
            }
            return MD5;
        }
    }

    /**
     * 加密文本
     *
     * @param encryptionType 加密方式
     * @param sourceText     原文(区分大小写)
     * @return
     */
    public static String encryptText(EncryptionType encryptionType, String sourceText) {
        String encryptedText = "";
        switch (encryptionType) {
            case MD5:
                encryptedText = DigestUtils.md5DigestAsHex(sourceText.getBytes());
                break;
            case SHA256:
                break;
            default:
                encryptedText = DigestUtils.md5DigestAsHex(sourceText.getBytes());
                break;
        }
        return encryptedText;
    }

    /**
     * 加密密码
     *
     * @param encryptionType 加密方式
     * @param sourcePassword 明文密码
     * @return
     */
    public static String encryptPassword(EncryptionType encryptionType, String sourcePassword) {
        String salt = RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
        sourcePassword = String.format("%s%s", sourcePassword, salt);
        String encryptedPassword = encryptText(encryptionType, sourcePassword);
        return String.format("%s%s%s%s%s", encryptionType.getType(), SEPARATOR, salt, SEPARATOR, encryptedPassword);
    }

    /**
     * 验证加密文本
     *
     * @param encryptionType 加密类型
     * @param sourceText     原文
     * @param encryptedText  密文
     * @return
     */
    public static boolean validateEncryptText(EncryptionType encryptionType, String sourceText, String encryptedText) {
        return encryptText(encryptionType, sourceText).equals(encryptedText);
    }

    /**
     * 验证密码
     *
     * @param sourcePassword    明文密码
     * @param encryptedPassword 加密后组合串
     * @return
     */
    public static boolean validateEncryptPassword(String sourcePassword, String encryptedPassword) {
        try {
            String[] split = encryptedPassword.split(SEPARATOR);
            EncryptionType encryptionType = EncryptionType.getEncryptionType(split[0]);
            String salt = split[1];
            encryptedPassword = split[2];
            sourcePassword = String.format("%s%s", sourcePassword, salt);
            return encryptText(encryptionType, sourcePassword).equals(encryptedPassword);
        } catch (Exception ex) {
            return false;
        }
    }
}
