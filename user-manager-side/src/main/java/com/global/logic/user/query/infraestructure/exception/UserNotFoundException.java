package com.global.logic.user.query.infraestructure.exception;

import com.global.logic.user.command.infrastructure.exception.CustomException;

public class UserNotFoundException extends CustomException {
    private static final int CUSTOM_ERROR_CODE = 1040;

    public UserNotFoundException(String message){
        super(CUSTOM_ERROR_CODE, message);
    }

    public UserNotFoundException(String message, Throwable cause){
        super(CUSTOM_ERROR_CODE, message, cause);
    }

    public static int getErrorCode() {
        return CUSTOM_ERROR_CODE;
    }
}
