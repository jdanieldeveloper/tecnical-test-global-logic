package com.global.logic.user.command.domain.user;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Value
public class Password {

    @NotNull(message = "{user.password.notnull}")
    @Pattern(regexp = "^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=(?:[^0-9]*[0-9]){2}[^0-9]*$)[A-Za-z0-9]{8,12}$",
             message = "{user.password.invalid.format}")
    private final String value;
}
    
