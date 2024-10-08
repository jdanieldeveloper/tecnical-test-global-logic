package com.global.logic.user.manager.domain.aggregate.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class IdentifierTest {

    @Test
    void userIdentifierSequenceIsValid() {
        UserIdentifier userIdentifier = new UserIdentifier(1L);

        assertEquals(1L, userIdentifier.getIdentifier());
    }

    @Test
    void userIdentifierSequenceIsNotValid() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UserIdentifier userIdentifier = new UserIdentifier(-1L);
        });

        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals(exception.getMessage(), "The identifier must be a correlative number!!!");
    }
}

