package com.kao.server.util.login;

/**
 * @author 全鸿润
 */
public class UidGenerator {

    public static int getUID() {

        return (int) System.currentTimeMillis();
    }
}
