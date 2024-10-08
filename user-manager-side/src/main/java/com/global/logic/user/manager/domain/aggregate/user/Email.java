package com.global.logic.user.manager.domain.aggregate.user;


import java.util.regex.Pattern;

public class Email {

    private final String value;

    private static final String VALID_REGEX_VALUE = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public Email(String value) {
        assertIfValidEmail(value);
        //
        this.value = value;
    }

    protected void assertIfValidEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("The password have a illegal format!!!");
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.compile(VALID_REGEX_VALUE, Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .matches();
    }

    public String getValue() {
        return value;
    }
}
    
