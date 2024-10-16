package com.global.logic.user.manager.domain.aggregate.user;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserId extends Identifier {

    @Min(value = 1, message = "The userId must be a correlative number")
    private final long value;


}
