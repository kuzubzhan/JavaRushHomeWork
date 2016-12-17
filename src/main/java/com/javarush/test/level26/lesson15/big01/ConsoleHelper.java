package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String result = "";
        try {
            result = br.readLine();
            if ("exit".equalsIgnoreCase(result))
                throw new InterruptOperationException();
        } catch (IOException e) {
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        writeMessage("Enter code");
        while ((result = readString()).length() != 3) writeMessage("Mistake");

        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] strings;
        writeMessage("Enter two integer");

        while (true) {
            strings = readString().split("\\s");
            try {
                if (strings.length == 2 && Integer.parseInt(strings[0]) > 0 && Integer.parseInt(strings[1]) > 0)
                    break;
                writeMessage("Mistake of data");
            } catch (Exception e) {
                writeMessage("Mistake of data and Exception");
            }
        }

        return strings;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Enter operation number : 1-4");
        String operation = readString();
        Operation result = null;
        try {
            result = Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
        } catch (Exception e) {
            askOperation();
        }

        return result;
    }
}
