package com.global.logic.user.query.infraestructure.service;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.config.WebSecurityConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import com.global.logic.user.query.application.service.UserQueryService;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import com.global.logic.user.query.infraestructure.util.UtilJwtToken;
import io.vavr.control.Either;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by daniel.carvajal
 *
 **/


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {UserManagerTestConfig.class, WebSecurityConfig.class})
@Transactional
public class UserQueryServiceITest {

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private UserQueryService userQueryService;

    private static PartyDto partyOriginalDto = getPartyDtoWithAllFieldsToSave();

    private static PartyDto partyToSavedDto = getPartyDtoWithAllFieldsToSave();

    @Test
    @Order(1)
    public void createAuthenticationTokenOk()  {
        // setup
        partyDao.saveUserWithRoles(partyToSavedDto);

        Either<UserAuthenticationException, String> generatedToken =
                userQueryService.createAuthenticationToken(partyOriginalDto.getUserLoginId(), partyOriginalDto.getCurrentPassword());

        assertTrue(generatedToken.isRight());
        assertNotNull(generatedToken.get());
    }

    @Test
    @Order(2)
    public void noCreateAuthenticationTokenBecauseBadCredentials()  {
        // setup
        partyDao.saveUserWithRoles(partyToSavedDto);

        Either<UserAuthenticationException, String> generatedToken =
                userQueryService.createAuthenticationToken(partyOriginalDto.getUserLoginId(), "failpass123");

        assertTrue(generatedToken.isLeft());
        assertEquals("Bad credentials", generatedToken.getLeft().getMessage());
    }

    @Test
    @Order(3)
    public void getUserByUserLoginIdOk()  {
        // setup
        partyDao.saveUserWithRoles(partyToSavedDto);

        Either<UserNotFoundException, PartyDto> foundPartyDto =
                userQueryService.getUserByUserLoginId(partyOriginalDto.getUserLoginId());

        assertTrue(foundPartyDto.isRight());
        assertNotNull(foundPartyDto.get());
    }

    @Test
    @Order(4)
    public void getUserByUserLoginIdNoOkBecauseUserNotFound()  {
        // setup
        partyDao.saveUserWithRoles(partyToSavedDto);

        Either<UserNotFoundException, PartyDto> foundPartyDto =
                userQueryService.getUserByUserLoginId("failMockUserLoginId");

        assertTrue(foundPartyDto.isLeft());
        assertEquals("The party by user login wasn't found!!!", foundPartyDto.getLeft().getMessage());
    }

    @Test
    @Order(5)
    public void getUserByUuidOk()  {
        // setup
        partyDao.saveUserWithRoles(partyToSavedDto);

        Either<UserNotFoundException, PartyDto> foundPartyDto =
                userQueryService.getUserByUuid(partyToSavedDto.getPartyUuid());

        assertTrue(foundPartyDto.isRight());
        assertNotNull(foundPartyDto.get());
    }

    @Test
    @Order(6)
    public void getUserByUuidNoOkBecauseUserNotFound()  {
        // setup
        partyDao.saveUserWithRoles(partyToSavedDto);

        Either<UserNotFoundException, PartyDto> foundPartyDto =
                userQueryService.getUserByUuid(partyOriginalDto.getPartyUuid());

        assertTrue(foundPartyDto.isLeft());
        assertEquals("The party by user uuid wasn't found!!!", foundPartyDto.getLeft().getMessage());
    }

    @Test
    @Order(7)
    public void validateAuthenticationTokenOk(){
        String token = UtilJwtToken.generateToken(
                new User(partyOriginalDto.getUserLoginId(), partyOriginalDto.getCurrentPassword(), List.of()));

        Either<UserAuthenticationException, Boolean> validatedToken =
                userQueryService.validateAuthenticationToken(
                        partyOriginalDto.getUserLoginId(), partyOriginalDto.getCurrentPassword(), token);

        assertTrue(validatedToken.isRight());
        assertNotNull(validatedToken.get());
    }

    @Test
    @Order(8)
    public void validateAuthenticationTokenNoOkBecauseUserAuthException() throws InterruptedException {
        String token = UtilJwtToken.generateToken(
                new User(partyOriginalDto.getUserLoginId(), partyOriginalDto.getCurrentPassword(), List.of()));

        Either<UserAuthenticationException, Boolean> validatedToken =
                userQueryService.validateAuthenticationToken(
                        "mockfailUserId", partyOriginalDto.getCurrentPassword(), token);

        assertTrue(validatedToken.isLeft());
        assertEquals("Error token is not valid or expire!!!", validatedToken.getLeft().getMessage());

    }
}
