package com.global.logic.user.manager.domain.aggregate.user;

import java.util.Optional;

public interface UserRepository {

    Optional<Identifier> getUserId();

    Optional<User> addUser(User user);

}