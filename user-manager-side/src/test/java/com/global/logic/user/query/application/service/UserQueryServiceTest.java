package com.global.logic.user.query.application.service;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.config.WebSecurityConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import com.global.logic.user.query.infraestructure.util.UtilJwtToken;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsSavedWithPassEncrypted;
import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * Created by daniel.carvajal
 *
 **/


@SpringBootTest
@ContextConfiguration(classes = {UserManagerTestConfig.class, WebSecurityConfig.class})
public class UserQueryServiceTest {

    @MockBean
    private PartyDao partyDao;

    @Autowired
    private UserQueryService userQueryService;

    private static final PartyDto partyOriginalDto = getPartyDtoWithAllFieldsToSave();

    private static final PartyDto partyToSavedDto = getPartyDtoWithAllFieldsSavedWithPassEncrypted();

    @Test
    public void createAuthenticationTokenOk()  {
        // setup
        when(partyDao.findPartyByUserLoginId(any())).thenReturn(partyToSavedDto);

        Either<UserAuthenticationException, String> generatedToken =
                userQueryService.createAuthenticationToken(partyOriginalDto.getUserLoginId(), partyOriginalDto.getCurrentPassword());

        assertTrue(generatedToken.isRight());
        assertNotNull(generatedToken.get());
    }

    @Test
    public void noCreateAuthenticationTokenBecauseBadCredentials()  {
        // setup
        when(partyDao.findPartyByUserLoginId(any())).thenReturn(partyToSavedDto);

        Either<UserAuthenticationException, String> generatedToken =
                userQueryService.createAuthenticationToken(partyOriginalDto.getUserLoginId(), "failpass123");

        assertTrue(generatedToken.isLeft());
        assertEquals("Bad credentials", generatedToken.getLeft().getMessage());
    }

    @Test
    public void getUserByUserLoginIdOk()  {
        // setup
        when(partyDao.findPartyByUserLoginId(any())).thenReturn(partyToSavedDto);

        Either<UserNotFoundException, PartyDto> foundPartyDto =
                userQueryService.getUserByUserLoginId(partyOriginalDto.getUserLoginId());

        assertTrue(foundPartyDto.isRight());
        assertNotNull(foundPartyDto.get());
    }

    @Test
    public void getUserByUserLoginIdNoOkBecauseUserNotFound()  {
        // setup
        when(partyDao.findPartyByUserLoginId(any()))
                .thenThrow(new UserNotFoundException("The party by user login wasn't found!!!"));

        Either<UserNotFoundException, PartyDto> foundPartyDto =
                userQueryService.getUserByUserLoginId("failMockUserLoginId");

        assertTrue(foundPartyDto.isLeft());
        assertEquals("The party by user login wasn't found!!!", foundPartyDto.getLeft().getMessage());
    }

    @Test
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
