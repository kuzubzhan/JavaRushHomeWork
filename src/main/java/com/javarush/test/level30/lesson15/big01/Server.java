package com.javarush.test.level30.lesson15.big01;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static class Handler extends Thread {
        private Socket socket;
        private Handler(Socket socket) {
            this.socket = socket;
        }
    }

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Input port server");
        int port = ConsoleHelper.readInt();
        try (ServerSocket socket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Server is started");
            while (true) {
                new Handler(socket.accept()).start();
            }
        }
        catch (Exception e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }
}
