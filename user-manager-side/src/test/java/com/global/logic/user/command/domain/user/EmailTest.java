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
public class EmailTest {

    @Autowired
    private Validator validator;


    @Test
    void emailFormatIsValid() {
        Email email = new Email("jdanieldeveloper@gmail.com");
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        assertTrue(violations.isEmpty());
    }


    @Test
    void emailFormatIsNotValidBecauseNullValue() {
        Email email = new Email(null);
        Set<ConstraintViolation<Email>> violations = validator.validate(email);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The user email can't be null");
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

