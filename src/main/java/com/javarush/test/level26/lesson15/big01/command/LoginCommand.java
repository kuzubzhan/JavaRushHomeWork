package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

class LoginCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Enter numberCard(12 digit) and pinCard(4 digit)");
            String str1 = ConsoleHelper.readString();
            String str2 = ConsoleHelper.readString();
            if (! str1.matches("\\d{12}") || ! str2.matches("\\d{4}")) {
                ConsoleHelper.writeMessage("No valid format");
            }
            else {
                if ("123456789012".equals(str1) && "1234".equals(str2)) {
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
