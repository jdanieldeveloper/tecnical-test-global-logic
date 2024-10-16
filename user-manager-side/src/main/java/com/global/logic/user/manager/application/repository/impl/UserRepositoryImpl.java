package com.global.logic.user.manager.application.repository.impl;

import com.global.logic.user.manager.domain.aggregate.user.*;
import com.global.logic.user.manager.infrastructure.converter.FromUserConverter;
import com.global.logic.user.manager.infrastructure.converter.IdentifierConverter;
import com.global.logic.user.manager.infrastructure.converter.ToUserConverter;
import com.global.logic.user.manager.infrastructure.persistence.dao.PartyDao;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@AllArgsConstructor // DI by constructor
public class UserRepositoryImpl implements UserRepository {

    private PartyDao partyDao;
    private ToUserConverter toUserConverter;
    private FromUserConverter fromUserConverter;

    @Override
    public Either<Throwable, UserId> getUserId() {
        return Try.of(() -> new UserId(partyDao.nexValueForIdentifier())).toEither();
    }

    @Override
    public Either<Throwable, UserUuid> getUserUuid() {
        return Try.of(() -> new UserUuid(UUID.randomUUID().toString())).toEither();
    }


    @Override
    public Either<Throwable, User> addUser(User user) {
        return Try.of(
                        Optional.ofNullable(user)
                                .map(fromUserConverter::convert)
                                .map(partyDao::saveUserWithRoles)
                                .map(toUserConverter::convert)::get)
                .toEither();

    }
}
