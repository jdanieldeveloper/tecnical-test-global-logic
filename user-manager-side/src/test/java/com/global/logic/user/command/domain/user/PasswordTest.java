package com.global.logic.user.command.domain.user;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ContextConfiguration(classes = { UserManagerTestConfig.class})
public class PasswordTest {

    @Autowired
    private Validator validator;

    @Test
    void passwordFormatIsValid() {
        Password password = new Password("m1Passw2rd");
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        assertTrue(violations.isEmpty());
    }


    @Test
    void passwordFormatIsInvalidBecauseIsNullField() {
        Password password = new Password(null);
        Set<ConstraintViolation<Password>> violations = validator.validate(password);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user password can't be null");
    }

    @Test
    void passwordFormatConsecutiveNumbersIsValid() {
        Password password = new Password("m12Passwrd");
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        assertTrue(violations.isEmpty());
    }

    @Test
    void passwordFormatIsNotValidBecauseMoreThan12Char() {
        Password password = new Password("miPassw12rddddddd");
        Set<ConstraintViolation<Password>> violations = validator.validate(password);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The password have a illegal format");
    }

    @Test
    void passwordFormatIsNotValidBecauseLessThan8Char() {
        Password password = new Password("miPas");
        Set<ConstraintViolation<Password>> violations = validator.validate(password);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The password have a illegal format");
    }

    @Test
    void passwordFormatIsNotValidBecauseNotHaveOnly1UpperLetter() {
        Password password = new Password("m1PassW2rd");
        Set<ConstraintViolation<Password>> violations = validator.validate(password);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The password have a illegal format");
    }
}

