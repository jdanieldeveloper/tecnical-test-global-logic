package com.global.logic.user.command.infrastructure.exception;

public class DatabaseException extends CustomException {

    private static final int CUSTOM_ERROR_CODE = 1020;

    public DatabaseException(String message){
        super(CUSTOM_ERROR_CODE, message);
    }

    public DatabaseException(String message, Throwable cause){
        super(CUSTOM_ERROR_CODE, message, cause);
    }

    public static int getErrorCode() {
        return CUSTOM_ERROR_CODE;
    }
}
