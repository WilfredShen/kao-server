package com.kao.server.util.token;

public class TokenContant {

    private static final long EXPIRTIME = 30 * 60 * 1000;
    private static final String SECRET_KEY = "we35nhjkd7kfir9fdregb32n348mz67b";

    public static long getEXPIRTIME() {
        return EXPIRTIME;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }
}
