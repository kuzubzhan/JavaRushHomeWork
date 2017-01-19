package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true) {
            ConsoleHelper.writeMessage("Enter amount");
            String str = ConsoleHelper.readString();
            try {
                int amount = Integer.parseInt(str);

                if (amount <= 0) {
                    ConsoleHelper.writeMessage("Amount <= 0");
                    continue;
                }

                if (! cm.isAmountAvailable(amount)) {
                    ConsoleHelper.writeMessage("Amount to mach");
                    continue;
                }

                cm.withdrawAmount(amount);
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("NumberFormatException");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("NotEnoughMoneyException");
            }
        }
    }
}
