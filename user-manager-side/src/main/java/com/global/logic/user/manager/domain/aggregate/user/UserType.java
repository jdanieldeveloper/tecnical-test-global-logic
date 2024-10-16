package com.global.logic.user.manager.domain.aggregate.user;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserType extends Identifier {

	@NotBlank(message = "The user type mustn't be a empty value")
	private final String value;

}
