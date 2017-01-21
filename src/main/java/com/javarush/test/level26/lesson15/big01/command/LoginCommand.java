package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String str1 = ConsoleHelper.readString();
            String str2 = ConsoleHelper.readString();
            if (! validCreditCards.containsKey(str1)) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), str1));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
            else {
                if (validCreditCards.getString(str1).equals(str2)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), str1));
                    break;
                }
                else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), str1));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }
        }
    }
}
