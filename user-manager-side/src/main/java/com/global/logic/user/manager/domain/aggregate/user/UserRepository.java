package com.global.logic.user.manager.domain.aggregate.user;

import com.global.logic.user.manager.domain.aggregate.Repository;
import io.vavr.control.Either;

import java.util.Optional;

public interface UserRepository extends Repository {

    Either<Throwable, UserId> getUserId();

    Either<Throwable, UserUuid> getUserUuid();

    Either<Throwable, User> addUser(User user);

}