package com.global.logic.user.command.domain.aggregate.user;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserUuid extends Identifier {

    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
            message = "The uuid is invalid value")
    private final String value;
}
