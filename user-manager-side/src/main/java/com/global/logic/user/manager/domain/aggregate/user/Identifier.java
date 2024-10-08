package com.global.logic.user.manager.domain.aggregate.user;


public abstract class Identifier implements Id {

	protected void assertIfValidIdentifier(Long id) {}

	protected void assertIfValidIdentifier(String id) {}

}
