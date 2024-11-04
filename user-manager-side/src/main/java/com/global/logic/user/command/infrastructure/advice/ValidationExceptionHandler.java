package com.global.logic.user.command.infrastructure.advice;

import com.global.logic.user.command.infrastructure.api.factory.ResponseFactory;
import com.global.logic.user.command.infrastructure.exception.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<Throwable> errors = Optional.ofNullable(exception)
                .map(MethodArgumentNotValidException::getAllErrors)
                .stream()
                .flatMap(Collection::stream)
                .map(error -> new DomainException(
                        MessageFormat.format("Error validation [{0} : {1}]",
                                ((FieldError) error).getField(), error.getDefaultMessage())))
                .collect(Collectors.toList());

        return ResponseFactory.createError(DomainException.class, errors);
    }
}
