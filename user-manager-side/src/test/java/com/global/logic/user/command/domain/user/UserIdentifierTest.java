package com.global.logic.user.command.domain.user;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@ContextConfiguration(classes = { UserManagerTestConfig.class})
public class UserIdentifierTest {

    @Autowired
    private Validator validator;


    @Test
    void userIdSequenceIsValid() {
        UserId userId = new UserId(1L);
        Set<ConstraintViolation<UserId>> violations = validator.validate(userId);

        assertTrue(violations.isEmpty());
    }

    @Test
    void userIdSequenceIsNotValidBecauseIsNull() {
        UserId userId = new UserId(null);
        Set<ConstraintViolation<UserId>> violations = validator.validate(userId);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user Identifier can't be null");
    }

    @Test
    void userIdSequenceIsNotValid() {
        UserId userId = new UserId(-1L);
        Set<ConstraintViolation<UserId>> violations = validator.validate(userId);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The userId must be a correlative number");
    }

    @Test
    void userUuidIsValid() {
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString());
        Set<ConstraintViolation<UserUuid>> violations = validator.validate(userUuid);

        assertTrue(violations.isEmpty());
    }

    @Test
    void userUuidIsNotValid() {
        // error 13 char in the las group
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString() + "0");
        Set<ConstraintViolation<UserUuid>> violations = validator.validate(userUuid);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The uuid is invalid value");
    }
}

