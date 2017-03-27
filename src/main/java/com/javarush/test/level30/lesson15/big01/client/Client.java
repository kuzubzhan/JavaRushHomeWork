package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread {
    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Enter address of server");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Enter port of server");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Enter name of user");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Message did not sent");
            clientConnected = false;
        }
    }
}
