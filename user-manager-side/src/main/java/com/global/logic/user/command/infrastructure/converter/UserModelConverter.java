package com.global.logic.user.command.infrastructure.converter;

import com.global.logic.user.command.infrastructure.api.model.ErrorModelResp;
import com.global.logic.user.command.infrastructure.exception.CustomException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;


@Component
public class UserModelConverter {

    public static Function<CustomException, ErrorModelResp.CustomError> customExceptionToCustomError = exception ->
            ErrorModelResp.CustomError.builder()
                    .timeStamp(LocalDateTime.now())
                    .code(exception.getCode())
                    .detail(exception.getMessage())
                    .build();
}