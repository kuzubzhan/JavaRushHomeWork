package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;
        private Handler(Socket socket) {
            this.socket = socket;
        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message userName = connection.receive();
                if (MessageType.USER_NAME.equals(userName.getType())) {
                    String name = userName.getData();
                    if (name != null && !name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }
                }
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Message is not sent");
            }
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
