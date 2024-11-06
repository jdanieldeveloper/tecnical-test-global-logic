package com.global.logic.user.command.domain.user;


import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
public class Email {

    @NotNull(message = "{user.email.notnull}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{email.invalid.format}")
    private final String value;
}
    
