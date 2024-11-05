package com.global.logic.user.query.infraestructure.api;


import com.global.logic.user.command.infrastructure.api.factory.ResponseFactory;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.query.application.gateway.UserQueryGateway;
import com.global.logic.user.query.infraestructure.api.model.LoginModelReq;
import com.global.logic.user.query.infraestructure.api.model.LoginModelResp;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import com.global.logic.user.query.infraestructure.util.UtilJwtToken;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.global.logic.user.command.infrastructure.converter.UserModelConverter.userPhoneDtosToPhoneModels;

@Slf4j
@RestController
@AllArgsConstructor // DI by constructor
public class UserQueryApi {

    private UserQueryGateway userQueryGateway;

    @PostMapping(value = "/api/v1/query/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginModelReq request, HttpServletRequest httpRequest) {
        // validate token integrity
        Either<UserAuthenticationException, Boolean> validAuthToken =
                userQueryGateway.validateAuthenticationToken(
                        request.getEmail(), request.getPassword(), UtilJwtToken.resolveToken(httpRequest));
        if (validAuthToken.isLeft()) {
            return ResponseFactory.createError(UserAuthenticationException.class, List.of(validAuthToken.getLeft()));
        }

        // auth user and generate token
        Either<UserAuthenticationException, String> userAuthToken =
                userQueryGateway.createAuthenticationToken(request.getEmail(), request.getPassword());
        if (userAuthToken.isLeft()) {
            return ResponseFactory.createError(UserAuthenticationException.class, List.of(userAuthToken.getLeft()));

        }
        log.info("User [{}] has been auth correctly!!!", request.getEmail());

        // get user by user login
        Either<UserNotFoundException, PartyDto> foundPartyDto =
                userQueryGateway.getUserByUserLoginId(request.getEmail());
        if (foundPartyDto.isLeft()) {
            return ResponseFactory.createError(UserNotFoundException.class, List.of(foundPartyDto.getLeft()));

        }

        return ResponseFactory.createSuccess(
                LoginModelResp.builder()
                        .uuid(foundPartyDto.get().getPartyUuid())
                        .createdDate(foundPartyDto.get().getCreatedDate())
                        .lastLoginDate(foundPartyDto.get().getLastLoginDate())
                        .token(userAuthToken.get())
                        .isActive(foundPartyDto.get().isActive())
                        .name(foundPartyDto.get().getPartyName())
                        .email(foundPartyDto.get().getUserLoginId()) // email
                        .password(foundPartyDto.get().getCurrentPassword())
                        .phones(userPhoneDtosToPhoneModels.apply(foundPartyDto.get().getUserPhonesDtos()))
                        .build()
        );

    }
}
