package com.global.logic.user.query.application.service;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import com.global.logic.user.query.infraestructure.util.UtilJwtToken;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor // DI by constructor
public class UserQueryService {

    private PartyDao partyDao;
    private AuthenticationManager authenticationManager;

    public Either<UserNotFoundException, PartyDto> getUserByUserLoginId(String userLoginId) {
        Try<PartyDto> foundPartyDto = Try.of(() -> partyDao.findPartyByUserLoginId(userLoginId));
        if (foundPartyDto.isSuccess()) {
            return Either.right(foundPartyDto.get());
        } else {
            return Either.left(
                    new UserNotFoundException(foundPartyDto.getCause().getMessage(), foundPartyDto.getCause()));
        }
    }

    public Either<UserNotFoundException, PartyDto> getUserByUuid(String uuid) {
        Try<PartyDto> foundPartyDto = Try.of(() -> partyDao.findPartyByUuid(uuid));
        if (foundPartyDto.isSuccess()) {
            return Either.right(foundPartyDto.get());
        } else {
            return Either.left(
                    new UserNotFoundException(foundPartyDto.getCause().getMessage(), foundPartyDto.getCause()));
        }
    }


    public Either<UserAuthenticationException, String> createAuthenticationToken(String userLoginId, String password) {
        // auth user
        Try<Authentication> authenticate = Try.of(() ->
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginId, password)));

        if (authenticate.isSuccess()) {
            // by security default user
            return Either.right(UtilJwtToken.generateToken(
                    new User(authenticate.get().getName(), password, authenticate.get().getAuthorities())));

        } else {
            log.error("Error user auth wasn't successful!!!", authenticate.getCause());

            return Either.left(
                    new UserAuthenticationException(authenticate.getCause().getMessage(), authenticate.getCause()));
        }
    }

    public Either<UserAuthenticationException, Boolean> validateAuthenticationToken(
            String userLoginId, String password, String authToken) {
        // validate if userLogin and expiration is ok
        boolean isValidToken = UtilJwtToken.validateToken(authToken, new User(userLoginId, password, List.of()));

        if (isValidToken) {
            return Either.right(isValidToken);

        } else {
            log.error("Error token is not valid or expired!!!");

            return Either.left(
                    new UserAuthenticationException("Error token is not valid or expire!!!"));
        }
    }
}
