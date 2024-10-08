package com.global.logic.user.manager.domain.aggregate.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EmailTest {

    @Test
    void emailFormatIsValid() {
        Email email = new Email("jdanieldeveloper@gmail.com");

        assertEquals("jdanieldeveloper@gmail.com", email.getValue());
    }

    @Test
    void emailFormatIsNotValidBecauseWithOutDomain() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Email email = new Email("jdanieldeveloper@gmail");
        });

        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals(exception.getMessage(), "The email have a illegal format!!!");
    }

    @Test
    void emailFormatIsNotValidBecauseWithOutServer() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Email email = new Email("jdanieldeveloper");
        });

        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals(exception.getMessage(), "The email have a illegal format!!!");
    }
}

