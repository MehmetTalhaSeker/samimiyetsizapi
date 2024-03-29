package com.samimiyetsiz.samimiyetsizapi.util;
import java.security.SecureRandom;

public final class RandomUtil {
    private static final int DEF_COUNT = 20;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private RandomUtil() {
    }

    public static String generateRandomAlphanumericString() {
        return RandomStringUtils.random(20, 0, 0, true, true, (char[]) null, SECURE_RANDOM);
    }

    public static String generatePassword() {
        return generateRandomAlphanumericString();
    }

    public static String generateActivationKey() {
        return generateRandomAlphanumericString();
    }

    static {
        SECURE_RANDOM.nextBytes(new byte[64]);
    }
}