package com.global.logic.user.command.infrastructure.exception;

public class DomainException extends CustomException {

    private static final int CUSTOM_ERROR_CODE = 1010;

    public DomainException(String message){
        super(CUSTOM_ERROR_CODE, message);
    }

    public DomainException(String message, Throwable cause){
        super(CUSTOM_ERROR_CODE, message, cause);
    }

    public static int getErrorCode() {
        return CUSTOM_ERROR_CODE;
    }
}
