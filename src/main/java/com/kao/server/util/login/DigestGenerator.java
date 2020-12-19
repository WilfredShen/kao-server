package com.kao.server.util.login;

import com.kao.server.util.security.SecurityUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author 全鸿润
 */
public class DigestGenerator {

    public static String getDigest(String password, String salt) {
        byte[] bytes = SecurityUtil.sha256((password + salt).getBytes());
        if (bytes != null) {
            return SecurityUtil.encodeBase64(bytes);
        }
        return null;
    }
}
