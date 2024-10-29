package com.global.logic.user.command.infrastructure.exception;


import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private int code;
    private static final int CUSTOM_ERROR_CODE = -1010;

    public CustomException(String message){
        super(message);
        this.code = CUSTOM_ERROR_CODE;
    }

    public CustomException(int code, String message){
        super(message);
        this.code = code;
    }

    public CustomException(int code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public static int getErrorCode() {
        return CUSTOM_ERROR_CODE;
    }
}
