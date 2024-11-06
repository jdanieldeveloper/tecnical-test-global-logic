package com.global.logic.user.command.domain.user;

import com.global.logic.user.command.domain.Repository;
import io.vavr.control.Either;

public interface UserRepository extends Repository {

    Either<Throwable, UserId> getUserId();

    Either<Throwable, UserUuid> getUserUuid();

    Either<Throwable, User> addUser(User user);

}