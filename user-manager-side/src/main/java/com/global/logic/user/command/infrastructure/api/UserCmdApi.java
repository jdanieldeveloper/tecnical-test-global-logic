package com.global.logic.user.command.infrastructure.api;


import com.global.logic.user.command.application.cmd.CreateUserCmd;
import com.global.logic.user.command.application.handler.CommandHandler;
import com.global.logic.user.command.infrastructure.api.factory.ResponseFactory;
import com.global.logic.user.command.infrastructure.api.model.CreateUserReq;
import com.global.logic.user.command.infrastructure.api.model.CreateUserResp;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.exception.CustomException;
import com.global.logic.user.query.application.gateway.UserQueryGateWay;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
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
    private UserQueryGateWay userQueryGateWay;

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

        if (!createUserCmd.hasErrors()) {
            log.info("User [{}] has been created correctly!!!", request.getName());

            // get data from store
            Either<UserNotFoundException, PartyDto> userCreated = userQueryGateWay.getUserByUuid(createUserCmd.getUserUuid());
            if (userCreated.isLeft()) {
                return ResponseFactory.createError(UserNotFoundException.class, List.of(userCreated.getLeft()));
            } else {
                return ResponseFactory.createSuccess(
                        CreateUserResp.builder()
                                .uuid(userCreated.get().getPartyUuid())
                                .createdDate(userCreated.get().getCreatedDate())
                                .lastLoginDate(userCreated.get().getLastLoginDate())
                                //.token()
                                .isActive(userCreated.get().isActive())
                                .build()
                );
            }
        } else {
            return ResponseFactory.createError(CustomException.class, createUserCmd.getErrors());
        }
    }
}
