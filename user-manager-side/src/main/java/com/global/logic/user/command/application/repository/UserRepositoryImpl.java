package com.global.logic.user.command.application.repository;

import com.global.logic.user.command.domain.user.User;
import com.global.logic.user.command.domain.user.UserId;
import com.global.logic.user.command.domain.user.UserRepository;
import com.global.logic.user.command.domain.user.UserUuid;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.global.logic.user.command.infrastructure.converter.UserConverter.partyDtoToUser;
import static com.global.logic.user.command.infrastructure.converter.UserConverter.userToPartyDto;

@Slf4j
@Component
@AllArgsConstructor // DI by constructor
public class UserRepositoryImpl implements UserRepository {

    private PartyDao partyDao;

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
        return Try.of(() ->
                        Optional.ofNullable(user)
                                .map(userToPartyDto)
                                .map(partyDao::saveUserWithRoles)
                                .map(partyDtoToUser)
                                .orElseThrow(IllegalStateException::new))
                .toEither();
    }
}
