package com.global.logic.user.command.domain.aggregate.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class PasswordTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void passwordFormatIsValid() {
        Password password = new Password("m1Passw2rd");
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        assertTrue(violations.isEmpty());
    }

    @Test
    void passwordFormatConsecutiveNumbersIsValid() {
        Password password = new Password("m1Passw2rd");
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

    //TODO afinar la expresion para que permita solo 1 Mayuscula
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

