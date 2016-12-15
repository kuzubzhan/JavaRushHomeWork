package com.javarush.test.level26.lesson15.big01;

public enum Operation {
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        switch (i) {
            case 1 :
                System.out.println("1");
                return INFO;
            case 2 :
                System.out.println("2");
                return DEPOSIT;
            case 3 :
                System.out.println("3");
                return WITHDRAW;
            case 4 :
                System.out.println("4");
                return EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
