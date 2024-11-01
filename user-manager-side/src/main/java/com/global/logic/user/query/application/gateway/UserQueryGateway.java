package com.global.logic.user.query.application.gateway;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.query.application.service.UserQueryService;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserQueryGateway {

    private UserQueryService userQueryService;

    public Either<UserNotFoundException, PartyDto> getUserByUserLoginId(String userLoginId) {
        return userQueryService.getUserByUserLoginId(userLoginId);
    }

    public Either<UserNotFoundException, PartyDto> getUserByUuid(String uuid) {
        return userQueryService.getUserByUuid(uuid);
    }

    public Either<UserAuthenticationException, String> createAuthenticationToken(String userLoginId, String password) {
        return userQueryService.createAuthenticationToken(userLoginId, password);
    }

    public Either<UserAuthenticationException, Boolean> validateAuthenticationToken(
            String userLoginId, String password, String authToken) {
        return userQueryService.validateAuthenticationToken(userLoginId, password, authToken);
    }
}
