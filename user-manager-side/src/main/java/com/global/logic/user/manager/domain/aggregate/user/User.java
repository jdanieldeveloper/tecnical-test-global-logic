package com.global.logic.user.manager.domain.aggregate.user;

import java.util.Optional;
import java.util.Set;


public class User {

	private final Id userId;
	private final Optional<String> name;
	private final Email email;
	private final Password password;
	private final Optional<Set<Phone>> phones;

	public User(Id userId,
				Optional<String> name,
				Email email,
				Password password,
				Optional<Set<Phone>> phones) {
		this.userId = userId;
		this.name = name;
		this.email =  email;
		this.password = password;
		this.phones = phones;
	}

	public Id getUserId() {
		return userId;
	}

	public String getName() {
		return name.orElseThrow(() -> new IllegalArgumentException("The name not exists"));
	}

	public Email getEmail() {
		return email;
	}

	public Password getPassword() {
		return password;
	}

	public Set<Phone> getPhones() {
		return phones.orElseThrow(() -> new IllegalArgumentException("The set of phones not exists"));
	}

	public boolean isExistsName() {
		return name.isPresent();
	}

	public boolean isExistsPhones() {
		return phones.isPresent();
	}
}
