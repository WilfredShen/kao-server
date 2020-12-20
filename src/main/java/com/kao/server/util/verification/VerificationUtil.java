package com.kao.server.util.verification;

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
    public static boolean realAuth(String identity, String name) {
        return true;
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
