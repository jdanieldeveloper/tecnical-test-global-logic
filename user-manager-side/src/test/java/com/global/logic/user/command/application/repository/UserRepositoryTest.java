package com.global.logic.user.command.application.repository;

import com.global.logic.user.command.domain.user.User;
import com.global.logic.user.command.domain.user.UserId;
import com.global.logic.user.command.domain.user.UserRepository;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static com.global.logic.user.command.infrastructure.converter.UserConverter.userToPartyDto;
import static com.global.logic.user.command.infrastructure.fixture.UserFixture.getUserWithAllFieldsToAdd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


/**
 * Created by daniel.carvajal
 *
 **/
@SpringBootTest
@ContextConfiguration(classes = UserManagerTestConfig.class)
public class UserRepositoryTest {

    @MockBean
    private PartyDao partyDao;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserIdOk()  {
        when(partyDao.nexValueForIdentifier()).thenReturn(1L);

        Either<Throwable, UserId>  userIdGenerated = userRepository.getUserId();
        assertTrue(userIdGenerated.isRight());
        assertEquals(1L, userIdGenerated.get().getValue());
    }

    @Test
    public void getUserIdNotOkBecauseThereIsDatabaseProblems()  {
        when(partyDao.nexValueForIdentifier())
                .thenThrow(new DatabaseException("There is problems with database"));

        Either<Throwable, UserId>  userIdGenerated = userRepository.getUserId();
        assertTrue(userIdGenerated.isLeft());
        assertEquals("There is problems with database", userIdGenerated.getLeft().getMessage());
    }


    //TODO see later
    /*@Test
    public void getUserUuidOK()  {
        try (MockedStatic<UUID> mockedStatic = mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID)
                    .thenReturn("e8b7b7d4-4c2d-4ebf-8f8d-6c9d21aef0b3");

            Either<Throwable, UserUuid>  userUuidGenerated = userRepository.getUserUuid();
            assertTrue(userUuidGenerated.isRight());
            assertEquals("e8b7b7d4-4c2d-4ebf-8f8d-6c9d21aef0b3", userUuidGenerated.get().getValue());
        }
    }*/


    //TODO see later
    /*@Test
    public void getUserUuidNotOkBecauseThereIsDatabaseProblems()  {
        try (MockedStatic<UUID> mockedStatic = mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID)
                    .thenThrow(new IllegalStateException("There is a problem when generate Uuid"));

            Either<Throwable, UserUuid>  userUuidGenerated = userRepository.getUserUuid();
            assertTrue(userUuidGenerated.isLeft());
            assertEquals("There is a problem when generate Uuid", userUuidGenerated.getLeft().getMessage());
        }
    }*/

    @Test
    public void addUserIdOk()  {
        User user = getUserWithAllFieldsToAdd();
        PartyDto partyDto = userToPartyDto.apply(user);

        when(partyDao.saveUserWithRoles(any(PartyDto.class))).thenReturn(partyDto);

        Either<Throwable, User>  userAdded = userRepository.addUser(user);
        assertTrue(userAdded.isRight());
        assertEquals(user, userAdded.get());
    }

    @Test
    public void addUserIdNoOkBecauseThereIsDatabaseProblems()  {
        User user = getUserWithAllFieldsToAdd();

        when(partyDao.saveUserWithRoles(any(PartyDto.class)))
                .thenThrow(new DatabaseException("There is problems with database"));

        Either<Throwable, User>  userAdded = userRepository.addUser(user);
        assertTrue(userAdded.isLeft());
        assertEquals("There is problems with database", userAdded.getLeft().getMessage());
    }
}
