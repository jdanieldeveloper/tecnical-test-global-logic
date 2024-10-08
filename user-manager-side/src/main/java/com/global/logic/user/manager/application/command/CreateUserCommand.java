package com.global.logic.user.manager.application.command;

import lombok.Value;

@Value
public class CreateUserCommand {
	public String userLogin;
	public String currentPassword;
}
