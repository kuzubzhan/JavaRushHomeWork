package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Enter numberCard(12 digit) and pinCard(4 digit)");
            String str1 = ConsoleHelper.readString();
            String str2 = ConsoleHelper.readString();
            if (! validCreditCards.containsKey(str1)) {
                ConsoleHelper.writeMessage("No valid format");
            }
            else {
                if (validCreditCards.getString(str1).equals(str2)) {
                    ConsoleHelper.writeMessage("Verification OK");
                    break;
                }
                else {
                    ConsoleHelper.writeMessage("No valid verification");
                }
            }
        }
    }
}
