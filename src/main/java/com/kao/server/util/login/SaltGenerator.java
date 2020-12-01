package com.kao.server.util.login;

/**
 * @author 全鸿润
 */
public class SaltGenerator {

    private static final String SALT_NUMBERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getSaltNumbers() {
        return SALT_NUMBERS;
    }

    public static String getSalt() {

        StringBuilder builder = new StringBuilder();
        char[] saltNumbers = SaltGenerator.getSaltNumbers().toCharArray();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * (saltNumbers.length) - 1);
            builder.append(saltNumbers[index]);
        }
        return builder.toString();
    }
}
