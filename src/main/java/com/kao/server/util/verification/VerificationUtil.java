package com.kao.server.util.verification;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 沈伟峰
 */
public class VerificationUtil {

    /**
     * 实名认证
     *
     * @param identity 身份证号
     * @param name     姓名
     * @return 认证结果
     */
    public static boolean identityAuth(String identity, String name) {
        boolean flag = false;
        try {
            flag = IdentityUtil.verify(identity, name);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 学生身份认证
     *
     * @param cid 院校编号
     * @param sid 学号
     * @return 认证结果
     */
    public static boolean studentAuth(String cid, String sid) {
        return true;
    }

    /**
     * 导师身份认证
     *
     * @param cid 院校编号
     * @param tid 导师编号
     * @return 认证结果
     */
    public static boolean tutorAuth(String cid, String tid) {
        return true;
    }
}
