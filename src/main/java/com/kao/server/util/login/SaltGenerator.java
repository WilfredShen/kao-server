package com.kao.server.util.login;

public class SaltGenerator {

    private static final String saltNumbers = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getSaltnumbers() {
        return new String(saltNumbers);
    }

    public static String getSalt() {

        StringBuilder builder = new StringBuilder();
        char[] saltnumbers = SaltGenerator.getSaltnumbers().toCharArray();
        int index = 0;
        for (int i = 0; i < 8; i++) {
            index = (int) (Math.random() * 36);
            builder.append(String.valueOf(saltnumbers[index]));
        }
        return builder.toString();
    }
}
