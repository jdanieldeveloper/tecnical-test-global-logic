package com.global.logic.user.command.domain.aggregate.user;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class Type {

    @NotBlank(message = "The typeId mustn't be a empty value")
    private final String typeId;

}
    
               
