package com.global.logic.user.manager.domain.aggregate.user;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserUuid extends Identifier {

    @NotBlank(message = "The uuid mustn't be a empty value")
    private final String value;
}
