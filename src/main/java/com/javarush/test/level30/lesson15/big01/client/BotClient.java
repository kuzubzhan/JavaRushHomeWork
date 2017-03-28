package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] nameAndText = message.split(": ");
            if (nameAndText.length == 2) {
                String name = nameAndText[0];
                String text = nameAndText[1];
                String str = "Информация для " + name + ": ";
                Date date = Calendar.getInstance().getTime();
                if ("дата".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("d.MM.YYYY").format(date));
                } else if ("день".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("d").format(date));
                } else if ("месяц".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("MMMM").format(date));
                } else if ("год".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("YYYY").format(date));
                } else if ("время".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("H:mm:ss").format(date));
                } else if ("час".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("H").format(date));
                } else if ("минуты".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("m").format(date));
                } else if ("секунды".equals(text)) {
                    sendTextMessage(str + new SimpleDateFormat("s").format(date));
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
