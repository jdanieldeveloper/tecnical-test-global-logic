package com.global.logic.user.manager.domain.aggregate.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PasswordTest {

    @Test
    void passwordFormatIsValid() {
        Password password = new Password("m1Passw2rd");

        assertEquals("m1Passw2rd", password.getValue());
    }

    @Test
    void passwordFormatConsecutiveNumbersIsValid() {
        Password password = new Password("miPassw12rd");

        assertEquals("miPassw12rd", password.getValue());
    }

    @Test
    void passwordFormatIsNotValidBecauseMoreThan12Char() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Password password = new Password("miPassw12rddddddd");
        });

        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals(exception.getMessage(), "The password have a illegal format!!!");
    }

    @Test
    void passwordFormatIsNotValidBecauseLessThan8Char() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Password password = new Password("miPas");
        });

        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals(exception.getMessage(), "The password have a illegal format!!!");
    }

    //TODO afinar la expresion para que permita solo 1 Mayuscula
    @Test
    void passwordFormatIsNotValidBecauseNotHaveOnly1UpperLetter() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Password password = new Password("m1PassW2rd");
        });

        assertEquals(exception.getClass(), IllegalArgumentException.class);
        assertEquals(exception.getMessage(), "The password have a illegal format!!!");
    }



}

