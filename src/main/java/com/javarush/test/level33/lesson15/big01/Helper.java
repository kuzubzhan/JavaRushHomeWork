package com.javarush.test.level33.lesson15.big01;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {

    public static String generateRandomString() {
        BigInteger bi = new BigInteger(200, new SecureRandom());
        return bi.toString(36);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
