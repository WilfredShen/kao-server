package com.kao.server.util.verification;

/**
 * @author 沈伟峰
 */
public class VerificationUtil {

    public static boolean realAuth(String identity, String name) {
        return true;
    }

    public static boolean studentAuth(String cid, String sid) {
        return true;
    }

    public static boolean tutorAuth(String cid, String tid) {
        return true;
    }
}
