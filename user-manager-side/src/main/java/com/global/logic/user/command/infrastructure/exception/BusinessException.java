package com.global.logic.user.command.infrastructure.exception;

public class BusinessException extends CustomException {

    private static final int CUSTOM_ERROR_CODE = 1030;

    public BusinessException(String message){
        super(CUSTOM_ERROR_CODE, message);
    }

    public BusinessException(String message, Throwable cause){
        super(CUSTOM_ERROR_CODE, message, cause);
    }

    public static int getErrorCode() {
        return CUSTOM_ERROR_CODE;
    }
}
