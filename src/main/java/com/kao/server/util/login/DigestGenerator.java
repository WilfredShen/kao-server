package com.kao.server.util.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author 全鸿润
 */
public class DigestGenerator {

    public static String getDigest(String password, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest((password + salt).getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
