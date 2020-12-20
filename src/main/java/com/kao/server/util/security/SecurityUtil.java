package com.kao.server.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author 沈伟峰
 */
public class SecurityUtil {

    /**
     * 将传入的字节序列使用 SHA256 算法进行摘要
     *
     * @param bytes 进行摘要的字节序列
     * @return 摘要结果
     */
    public static byte[] sha256(byte[] bytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将传入的字节序列使用 BASE64 编码方式进行编码
     *
     * @param bytes 进行编码的字节序列
     * @return 编码生成的字符串
     */
    public static String encodeBase64(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }
}
