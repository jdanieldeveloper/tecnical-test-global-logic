package com.global.logic.user.command.infrastructure.api.factory;

import com.global.logic.user.command.infrastructure.api.model.ErrorModelResp;
import com.global.logic.user.command.infrastructure.exception.BusinessException;
import com.global.logic.user.command.infrastructure.exception.CustomException;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.exception.DomainException;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by daniel.carvajal
 *
 **/
@SpringBootTest
public class ResponseFactoryTest {

    @Test
    public void createResponseHttp409ConflictErrorWhenIsBusinessException(){
        ResponseEntity<?> response = ResponseFactory.createError(
                BusinessException.class, List.of(new BusinessException("There is Business problem")));
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        ErrorModelResp errorModelResp = (ErrorModelResp) response.getBody();
        assertNotNull(errorModelResp.getCustomErrors());
        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getCode() == BusinessException.getErrorCode()));

        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getDetail().equals( "There is Business problem")));
    }

    @Test
    public void createResponseHttp409ConflictErrorWhenIsDomainException(){
        ResponseEntity<?> response = ResponseFactory.createError(
                DomainException.class, List.of(new DomainException("There is Domain problem")));
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        ErrorModelResp errorModelResp = (ErrorModelResp) response.getBody();
        assertNotNull(errorModelResp.getCustomErrors());
        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getCode() == DomainException.getErrorCode()));

        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getDetail().equals( "There is Domain problem")));
    }

    @Test
    public void createResponseHttp500InternalServerErrorWhenIsDataBaseException(){
        ResponseEntity<?> response = ResponseFactory.createError(
                DatabaseException.class, List.of(new DatabaseException("There is Database problem")));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        ErrorModelResp errorModelResp = (ErrorModelResp) response.getBody();
        assertNotNull(errorModelResp.getCustomErrors());
        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getCode() == DatabaseException.getErrorCode()));

        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getDetail().equals( "There is Database problem")));
    }

    @Test
    public void createResponseHttp500InternalServerErrorWhenIsCustomException(){
        ResponseEntity<?> response = ResponseFactory.createError(
                CustomException.class, List.of(new CustomException("There is any Custom problem")));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        ErrorModelResp errorModelResp = (ErrorModelResp) response.getBody();
        assertNotNull(errorModelResp.getCustomErrors());
        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getCode() == CustomException.getErrorCode()));

        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getDetail().equals( "There is any Custom problem")));
    }

    @Test
    public void createResponseHttp404NotFoundErrorWhenIsUserNotFoundException(){
        ResponseEntity<?> response = ResponseFactory.createError(
                UserNotFoundException.class, List.of(new UserNotFoundException("There is any User not found problem")));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        ErrorModelResp errorModelResp = (ErrorModelResp) response.getBody();
        assertNotNull(errorModelResp.getCustomErrors());
        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getCode() == UserNotFoundException.getErrorCode()));

        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getDetail().equals( "There is any User not found problem")));
    }

    @Test
    public void createResponseHttp401UnauthorizedErrorWhenIsUserNotFoundException(){
        ResponseEntity<?> response = ResponseFactory.createError(
                UserAuthenticationException.class, List.of(new UserAuthenticationException("There is Auth problem")));
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        ErrorModelResp errorModelResp = (ErrorModelResp) response.getBody();
        assertNotNull(errorModelResp.getCustomErrors());
        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getCode() == UserAuthenticationException.getErrorCode()));

        assertTrue(errorModelResp.getCustomErrors().stream()
                .anyMatch(customError -> customError.getDetail().equals( "There is Auth problem")));
    }
}
