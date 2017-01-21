package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException {
        String result = "";
        try {
            result = br.readLine();
            if (res.getString("operation.EXIT").equalsIgnoreCase(result))
                throw new InterruptOperationException();
        } catch (IOException e) {
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        writeMessage(res.getString("choose.currency.code"));
        while ((result = readString()).length() != 3) writeMessage(res.getString("invalid.data"));

        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] strings;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));

        while (true) {
            strings = readString().split("\\s");
            try {
                if (strings.length == 2 && Integer.parseInt(strings[0]) > 0 && Integer.parseInt(strings[1]) > 0)
                    break;
                writeMessage(res.getString("invalid.data"));
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
            }
        }

        return strings;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation") + " \n" +
                res.getString("operation.INFO") + ": 1;\n" +
                res.getString("operation.DEPOSIT") + ": 2;\n" +
                res.getString("operation.WITHDRAW") + ": 3;\n" +
                res.getString("operation.EXIT") + ": 4");
        String operation = readString();
        Operation result = null;
        try {
            result = Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
        } catch (Exception e) {
            writeMessage(res.getString("invalid.data"));
            askOperation();
        }

        return result;
    }
}
