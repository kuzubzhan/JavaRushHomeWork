package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        boolean money = false;
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> cm = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        for (CurrencyManipulator curr : cm) {
            money = curr.hasMoney();
            if (money && curr.getTotalAmount() > 0) {
                ConsoleHelper.writeMessage(curr.getCurrencyCode() + " - " + curr.getTotalAmount());
            }
        }
        if (!money) ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
