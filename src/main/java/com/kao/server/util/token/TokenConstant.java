package com.kao.server.util.token;

/**
 * @author 全鸿润
 */
public class TokenConstant {

    private static final long EXPIRED_TIME = 30 * 60 * 1000;
    private static final String SECRET_KEY = "we35nhjkd7kfir9fdregb32n348mz67b";

    public static long getExpiredTime() {
        return EXPIRED_TIME;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }
}
