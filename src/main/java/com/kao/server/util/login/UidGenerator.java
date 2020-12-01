package com.kao.server.util.login;

import java.util.Random;

/**
 * @author 全鸿润
 */
public class UidGenerator {

    private static int UID = 0;

    public synchronized static int getUid() {
        int res = new Random().nextInt(1000000000);
        if (UID == res) {
            UID += 1;
            return UID;
        }
        UID = res;
        return UID;
    }
}
