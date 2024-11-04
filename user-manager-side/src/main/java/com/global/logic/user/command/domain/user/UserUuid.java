package com.global.logic.user.command.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserUuid extends Identifier {

    @NotNull(message = "{user.uuid.identifier.notnull}")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
            message = "{user.uuid.identifier.format}")
    private final String value;
}
