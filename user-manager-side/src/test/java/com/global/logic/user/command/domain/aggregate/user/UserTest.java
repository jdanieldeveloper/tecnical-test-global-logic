package com.global.logic.user.command.domain.aggregate.user;

import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

import static com.global.logic.user.command.infrastructure.fixture.UserFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void userIsValid() {
        User user =  getUserWithAllFieldsToAdd();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertTrue(violations.isEmpty());
    }

    @Test
    void userIsInvalidBecauseUserIdIsNull() {
        User user = getUserWithUserIdNullField();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user Identifier can't be null");
    }

    @Test
    void userIsInvalidBecauseUserUuidIsNull() {
        User user = getUserWithUserUuidNullField();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user uuid can't be null");
    }

    @Test
    void userIsInvalidBecauseUserTypeIsNull() {
        User user = getUserWithUserTypeNullField();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user type can't be null");
    }

    @Test
    void userIsInvalidBecauseUserEmailIsNull() {
        User user = getUserWithUserEmailNullField();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user email can't be null");
    }

    @Test
    void userIsInvalidBecauseUserPasswordIsNull() {
        User user = getUserWithUserPasswordNullField();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user password can't be null");
    }
}

