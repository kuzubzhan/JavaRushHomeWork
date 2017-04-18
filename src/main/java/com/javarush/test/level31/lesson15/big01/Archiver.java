package com.javarush.test.level31.lesson15.big01;

import com.javarush.test.level31.lesson15.big01.exception.WrongZipFileException;

import java.io.IOException;

public class Archiver {
    public static void main(String[] args) throws Exception {
        while (true) {
            Operation operation = null;
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

            if (operation == Operation.EXIT) break;
        }
    }

    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("Выберите операцию:\n" +
                "\t" + Operation.CREATE.ordinal()  + " - упаковать файлы в архив\n" +
                "\t" + Operation.ADD.ordinal()     + " - добавить файл в архив\n" +
                "\t" + Operation.REMOVE.ordinal()  + " - удалить файл из архива\n" +
                "\t" + Operation.EXTRACT.ordinal() + " - распаковать архив\n" +
                "\t" + Operation.CONTENT.ordinal() + " - просмотреть содержимое архива\n" +
                "\t" + Operation.EXIT.ordinal()    + " – выход");

        int operationNumber = ConsoleHelper.readInt();

        Operation result = null;
        if (operationNumber == Operation.CREATE.ordinal()) result = Operation.CREATE;
        else if (operationNumber == Operation.ADD.ordinal()) result = Operation.ADD;
        else if (operationNumber == Operation.REMOVE.ordinal()) result = Operation.REMOVE;
        else if (operationNumber == Operation.EXTRACT.ordinal()) result = Operation.EXTRACT;
        else if (operationNumber == Operation.CONTENT.ordinal()) result = Operation.CONTENT;
        else if (operationNumber == Operation.EXIT.ordinal()) result = Operation.EXIT;

        return result;
    }
}
