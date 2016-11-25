package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.servIsNotAccess);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.servIsNotAccess, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.userIsNotAuto);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.userIsNotAuto, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.userIsBann);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.userIsBann, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.accessIsDeni);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.accessIsDeni, cause);
        }
    }
    public final static class Constants {
        public static final String servIsNotAccess = "Server is not accessible for now.";
        public static final String userIsNotAuto = "User is not authorized.";
        public static final String userIsBann = "User is banned.";
        public static final String accessIsDeni = "Access is denied.";

    }
}