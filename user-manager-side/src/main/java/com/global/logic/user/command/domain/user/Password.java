package com.global.logic.user.command.domain.user;


import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Pattern;


@Value
@EqualsAndHashCode(callSuper = false)
public class Password {

    @Pattern(regexp = "^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=(?:[^0-9]*[0-9]){2}[^0-9]*$)[A-Za-z0-9]{8,12}$",
             message = "The password have a illegal format")
    private final String value;
}
    
