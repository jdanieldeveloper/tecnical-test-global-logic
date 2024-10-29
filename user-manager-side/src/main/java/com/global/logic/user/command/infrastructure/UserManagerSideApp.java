package com.global.logic.user.command.infrastructure;

import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagerSideApp {

	@Autowired
	private PartyDao partyDao;

	public static void main(String[] args) {
		SpringApplication.run(UserManagerSideApp.class, args);
	}
}
