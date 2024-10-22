package com.global.logic.user.command.domain.aggregate.user;

import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class UserTypeTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void userTypeIsValid() {
        Type userType =  new Type(UserTypeEnum.VISITOR.getTypeId());
        Set<ConstraintViolation<Type>> violations = validator.validate(userType);

        assertTrue(violations.isEmpty());
    }

    @Test
    void userTypeIsNotValid() {
        Type userType = new Type(null);
        Set<ConstraintViolation<Type>> violations = validator.validate(userType);
        assertFalse(violations.isEmpty());

        Optional<String> message =
                violations.stream().map(ConstraintViolation::getMessage).findFirst();
        assertTrue(message.isPresent());
        assertEquals(message.get(), "The typeId mustn't be a empty value");
    }
}

