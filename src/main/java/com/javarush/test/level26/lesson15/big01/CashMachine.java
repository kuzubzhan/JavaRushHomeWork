package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation op;
        do {
            op = ConsoleHelper.askOperation();
            CommandExecutor.execute(op);
        } while (op != Operation.EXIT);
    }
}
