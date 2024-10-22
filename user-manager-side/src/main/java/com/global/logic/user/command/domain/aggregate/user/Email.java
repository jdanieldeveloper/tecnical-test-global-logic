package com.global.logic.user.command.domain.aggregate.user;


import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
public class Email {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "The email have a illegal format")
    private final String value;
}
    
