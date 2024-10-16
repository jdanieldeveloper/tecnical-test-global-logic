package com.global.logic.user.manager.domain.aggregate.user;


import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Pattern;


@Value
@EqualsAndHashCode(callSuper = false)
public class Password {

    @Pattern(regexp = "^(?=.*[A-Z])(?=(?:[^0-9]*[0-9]){2}[^0-9]*$)[A-Za-z0-9]{8,12}$",
             message = "The password have a illegal format")
    private final String value;
}
    
