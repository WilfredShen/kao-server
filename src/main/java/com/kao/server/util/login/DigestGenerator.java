package com.kao.server.util.login;

import com.kao.server.util.security.SecurityUtil;

/**
 * @author 全鸿润
 */
public class DigestGenerator {

    /**
     * 生成密码和盐值的摘要
     *
     * @param password 密码
     * @param salt     盐值
     * @return 摘要
     */
    public static String getDigest(String password, String salt) {
        byte[] bytes = SecurityUtil.sha256((password + salt).getBytes());
        if (bytes != null) {
            return SecurityUtil.encodeBase64(bytes);
        }
        return null;
    }
}
