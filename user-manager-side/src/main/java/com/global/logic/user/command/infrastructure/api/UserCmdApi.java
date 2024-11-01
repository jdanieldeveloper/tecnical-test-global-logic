package com.global.logic.user.command.infrastructure.api;


import com.global.logic.user.command.application.cmd.CreateUserCmd;
import com.global.logic.user.command.application.handler.CommandHandler;
import com.global.logic.user.command.infrastructure.api.factory.ResponseFactory;
import com.global.logic.user.command.infrastructure.api.model.CreateUserReq;
import com.global.logic.user.command.infrastructure.api.model.CreateUserResp;
import com.global.logic.user.command.infrastructure.exception.CustomException;
import com.global.logic.user.query.application.gateway.UserQueryGateway;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController// DI by constructor
@AllArgsConstructor
public class UserCmdApi {

    private CommandHandler commandHandler;
    private UserQueryGateway userQueryGateway;

    @PostMapping(value = "/api/command/user/sign-up")
    public ResponseEntity<?> creteUser(@RequestBody CreateUserReq request) {

        // send params to create user
        CreateUserCmd createUserCmd = commandHandler.handle(
                CreateUserCmd
                        .builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .phones(request.getPhones())
                        .build());

        if (createUserCmd.hasErrors()) {
            return ResponseFactory.createError(CustomException.class, createUserCmd.getErrors());
        }
        log.info("User [{}] has been created correctly!!!", request.getName());

        // auth user and generate token
        Either<UserAuthenticationException, String> userAuthToken =
                userQueryGateway.createAuthenticationToken(createUserCmd.getEmail(), createUserCmd.getPassword());
        if (userAuthToken.isLeft()) {
            return ResponseFactory.createError(UserAuthenticationException.class, List.of(userAuthToken.getLeft()));
        }
        log.info("User [{}] has been auth correctly!!!", request.getName());

        return ResponseFactory.createSuccess(
                CreateUserResp.builder()
                        .uuid(createUserCmd.getUuid())
                        .createdDate(createUserCmd.getCreatedDate())
                        .lastLoginDate(createUserCmd.getLastLoginDate())
                        .token(userAuthToken.get())
                        .isActive(createUserCmd.isActive())
                        .build()
        );
    }
}
