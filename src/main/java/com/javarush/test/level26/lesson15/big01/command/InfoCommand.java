package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command {
    @Override
    public void execute() {
        boolean money = false;
        Collection<CurrencyManipulator> cm = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        for (CurrencyManipulator curr : cm) {
            money = curr.hasMoney();
            if (money && curr.getTotalAmount() > 0) {
                ConsoleHelper.writeMessage(curr.getCurrencyCode() + " - " + curr.getTotalAmount());
            }
        }
        if (!money) ConsoleHelper.writeMessage("No money available.");
    }
}
