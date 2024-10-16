package com.global.logic.user.manager.domain.aggregate.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class IdentifierTest {

    @Test
    void userIdentifierSequenceIsValid() {
        UserId userId = new UserId(1L);

        assertEquals(1L, userId.getIdentifier());
    }

    @Test
    void userIdentifierSequenceIsNotValid() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UserId userId = new UserId(-1L);
        });

        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals(exception.getMessage(), "The identifier must be a correlative number!!!");
    }
}

