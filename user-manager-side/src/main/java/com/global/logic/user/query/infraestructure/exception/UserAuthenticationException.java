package com.global.logic.user.query.infraestructure.exception;

import com.global.logic.user.command.infrastructure.exception.CustomException;

public class UserAuthenticationException extends CustomException {
    private static final int CUSTOM_ERROR_CODE = 1050;

    public UserAuthenticationException(String message){
        super(CUSTOM_ERROR_CODE, message);
    }

    public UserAuthenticationException(String message, Throwable cause){
        super(CUSTOM_ERROR_CODE, message, cause);
    }

    public static int getErrorCode() {
        return CUSTOM_ERROR_CODE;
    }
}
