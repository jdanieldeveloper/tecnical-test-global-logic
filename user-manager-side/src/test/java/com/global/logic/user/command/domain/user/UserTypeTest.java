package com.global.logic.user.command.domain.user;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = { UserManagerTestConfig.class})
public class UserTypeTest {

    @Autowired
    private Validator validator;

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
        assertEquals(message.get(), "The user type can't be null");
    }
}

