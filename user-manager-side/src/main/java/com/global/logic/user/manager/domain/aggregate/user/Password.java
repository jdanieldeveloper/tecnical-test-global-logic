package com.global.logic.user.manager.domain.aggregate.user;


import java.util.regex.Pattern;

public class Password {

    private final String value;

    private static final String VALID_REGEX_VALUE = "^(?=.*[A-Z])(?=(?:[^0-9]*[0-9]){2}[^0-9]*$)[A-Za-z0-9]{8,12}$";

    public Password(String value) {
        assertIfValidPassword(value);
        //
        this.value = value;
    }

    protected void assertIfValidPassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("The password have a illegal format!!!");
        }
    }

    private boolean isValidPassword(String password) {
        return Pattern.compile(VALID_REGEX_VALUE, Pattern.CASE_INSENSITIVE)
                .matcher(password)
                .matches();
    }

    public String getValue() {
        return value;
    }
}
    
