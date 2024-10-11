package com.global.logic.user.manager.domain.aggregate.user;

import com.global.logic.user.manager.domain.aggregate.Repository;

import java.util.Optional;

public interface UserRepository extends Repository {


    Optional<Identifier> getUserId();

    Optional<User> addUser(User user);

}