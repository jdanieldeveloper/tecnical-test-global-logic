package com.global.logic.user.command.application.handler;

import com.global.logic.user.command.application.cmd.CreateUserCmd;
import com.global.logic.user.command.infrastructure.bus.CommandBus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor  // DI by constructor
public class CommandHandler {

	private CommandBus commandBus;

	public CreateUserCmd handle(CreateUserCmd command) {
		return commandBus.handle(command);
	}

}
