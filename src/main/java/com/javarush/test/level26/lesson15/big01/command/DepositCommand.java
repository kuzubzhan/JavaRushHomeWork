package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

class DepositCommand implements Command {
    @Override
    public void execute() {
        String str = ConsoleHelper.askCurrencyCode();
        String[] strArr = ConsoleHelper.getValidTwoDigits(str);
        int i1 = Integer.parseInt(strArr[0]);
        int i2 = Integer.parseInt(strArr[1]);
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(str);
        cm.addAmount(i1, i2);
    }
}
