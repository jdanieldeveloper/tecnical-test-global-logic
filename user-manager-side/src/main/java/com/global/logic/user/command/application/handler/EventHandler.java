package com.global.logic.user.command.application.handler;

import com.global.logic.user.command.application.event.CreatedUserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventHandler {


	public void handler(CreatedUserEvent event) {
		//TODO send event to event bus
		//eventConverter.convert(event);
	}
}
