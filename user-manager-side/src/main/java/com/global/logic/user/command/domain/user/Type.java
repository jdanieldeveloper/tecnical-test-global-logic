package com.global.logic.user.command.domain.user;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class Type {

    @NotNull(message = "{user.type.notnull}")
    private final String typeId;

}
    
               
