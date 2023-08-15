package com.proxy.center.util;

import java.util.Random;

public class RandomUtils {
    private static ThreadLocal<Random> randomLocal = ThreadLocal.withInitial(Random::new);

    public static String randomString() {
        return String.valueOf(randomInt());
    }

    public static int randomInt() {
        return (randomLocal.get()).nextInt();
    }

    public static int randomInt(int r) {
        return (randomLocal.get()).nextInt(r);
    }

    public static int randomInt(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    public static int randomInt(int min, long max) {
        return min + (int) (Math.random() * (max - min));
    }

    public static String randomIntLength(int length) {
        StringBuilder random = new StringBuilder();
        for (int i = 0; i < length; i++) {
            random.append(randomInt(10));
        }
        return random.toString();
    }
}



