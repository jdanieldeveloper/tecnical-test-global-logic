package com.global.logic.user.command;

import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private PartyDao partyDao;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}