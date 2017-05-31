package com.javarush.test.level38.lesson06.home01;

public class ExceptionFactory {
    public static Throwable getException(Enum e) {
        if (e != null) {
            String m = e.name().replace('_', ' ').toLowerCase();
            m = m.substring(0, 1).toUpperCase() + m.substring(1, m.length());

            if (e instanceof ExceptionApplicationMessage) {
                return new Exception(m);
            }
            if (e instanceof ExceptionDBMessage) {
                return new RuntimeException(m);
            }
            if (e instanceof ExceptionUserMessage) {
                return new Error(m);
            }
        }

        return new IllegalArgumentException();
    }
}
