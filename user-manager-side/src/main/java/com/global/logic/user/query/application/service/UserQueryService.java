package com.global.logic.user.query.application.service;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {

    public Either<UserNotFoundException, PartyDto> getUserByUuid(String uuid) {
        return null;
    }

    public Either<UserNotFoundException, PartyDto> getUserToken(String uuid) {
        return null;
    }
}
