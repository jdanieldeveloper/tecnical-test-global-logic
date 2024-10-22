package com.global.logic.user.command.domain.aggregate.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class EmailTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    void emailFormatIsValid() {
        Email email = new Email("jdanieldeveloper@gmail.com");
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        assertTrue(violations.isEmpty());
    }

    @Test
    void emailFormatIsNotValidBecauseWithOutDomain() {
        Email email = new Email("jdanieldeveloper@gmail");
        Set<ConstraintViolation<Email>> violations = validator.validate(email);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The email have a illegal format");
    }

    @Test
    void emailFormatIsNotValidBecauseWithOutServer() {
        Email email = new Email("jdanieldeveloper");
        Set<ConstraintViolation<Email>> violations = validator.validate(email);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The email have a illegal format");
    }
}

