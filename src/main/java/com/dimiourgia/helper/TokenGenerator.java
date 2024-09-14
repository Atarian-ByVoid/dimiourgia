package com.dimiourgia.helper;

import java.security.SecureRandom;

public class TokenGenerator {
    private static final String PREFIX = "KRONOS-";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 15;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateUniqueString() {

        StringBuilder sb = new StringBuilder(PREFIX);
        for (int i = 0; i < LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

}
