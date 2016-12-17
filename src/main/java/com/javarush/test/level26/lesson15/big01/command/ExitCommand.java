package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Are you sure want to exit? enter: y or n");
        String str = ConsoleHelper.readString();
        if ("y".equalsIgnoreCase(str)) ConsoleHelper.writeMessage("by-by");
    }
}
