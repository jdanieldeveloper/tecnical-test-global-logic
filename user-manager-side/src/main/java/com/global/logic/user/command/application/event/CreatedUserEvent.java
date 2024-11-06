package com.global.logic.user.command.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CreatedUserEvent implements Event {

	@JsonProperty("userId")
	public String userId;

	@JsonProperty("userLogin")
	public String userLogin;

	// add fields to the projection
}
