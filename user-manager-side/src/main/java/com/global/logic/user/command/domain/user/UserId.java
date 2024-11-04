package com.global.logic.user.command.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserId extends Identifier {

    @NotNull(message = "{user.identifier.notnull}")
    @Min(value = 1, message = "{user.identifier.format}")
    private final Long value;


}
