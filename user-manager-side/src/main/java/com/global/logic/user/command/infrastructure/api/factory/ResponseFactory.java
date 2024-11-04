package com.global.logic.user.command.infrastructure.api.factory;

import com.global.logic.user.command.infrastructure.api.model.ErrorModelResp;
import com.global.logic.user.command.infrastructure.exception.*;

import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

import static com.global.logic.user.command.infrastructure.converter.UserModelConverter.customExceptionToCustomError;

public class ResponseFactory {

    public static ResponseEntity<?> createSuccess(Object body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<?> createError(Class<? extends CustomException> type, List<Throwable> errors) {
        return Match(type).of(
                Case($(clazz -> clazz.equals(BusinessException.class)),
                        response -> getHttp409ConflictStatusErrors(errors)),
                Case($(clazz -> clazz.equals(DomainException.class)),
                        response -> getHttp400BadRequestStatusErrors(errors)),
                Case($(clazz -> clazz.equals(DatabaseException.class)),
                        response -> getHttp500InternalServerStatusErrors(errors)),
                Case($(clazz -> clazz.equals(UserNotFoundException.class)),
                        response -> getHttp404NotFoundStatusErrors(errors)),
                Case($(clazz -> clazz.equals(UserAuthenticationException.class)),
                        response -> getHttp401UnauthorizedStatusErrors(errors)),
                Case($(),
                        response -> getHttp500InternalServerStatusErrors(errors))
        );
    }

    private static ResponseEntity<?> getHttp409ConflictStatusErrors(List<Throwable> errors) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                //.headers()
                .body(createCustomError(errors));
    }

    private static ResponseEntity<?> getHttp400BadRequestStatusErrors(List<Throwable> errors) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                //.headers()
                .body(createCustomError(errors));
    }

    private static ResponseEntity<?> getHttp500InternalServerStatusErrors(List<Throwable> errors) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                //.headers()
                .body(createCustomError(errors));
    }

    private static ResponseEntity<?> getHttp401UnauthorizedStatusErrors(List<Throwable> errors) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                //.headers()
                .body(createCustomError(errors));
    }

    private static ResponseEntity<?> getHttp404NotFoundStatusErrors(List<Throwable> errors) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                //.headers()
                .body(createCustomError(errors));
    }

    private static ErrorModelResp createCustomError(List<Throwable> errors) {
        return ErrorModelResp.builder()
                .customErrors(
                        errors.stream()
                                .filter(instanceOf(CustomException.class))
                                .map(exception -> (CustomException) exception)
                                .map(customExceptionToCustomError)
                                .collect(Collectors.toList()))
                .build();
    }
}
