package com.global.logic.user.command.application.aggregate;

import com.global.logic.user.command.application.cmd.CreateUserCmd;
import com.global.logic.user.command.domain.user.*;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

import static com.global.logic.user.command.infrastructure.fixture.UserCmdFixture.getCreateUserCmd;
import static com.global.logic.user.command.infrastructure.fixture.UserFixture.getUserWithAllFieldsToAdd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



/**
 * Created by daniel.carvajal
 *
 **/
@SpringBootTest
@ContextConfiguration(classes = UserManagerTestConfig.class)
public class UserAggregateTest {

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private UserAggregate userAggregate;

    @Test
    public void userStoreOk(){
        when(userRepository.getUserId()).thenReturn(Either.right(new UserId(1L)));
        when(userRepository.getUserUuid()).thenReturn(Either.right(new UserUuid("1-2-3-4-5-6")));
        when(userRepository.addUser(any(User.class))).thenReturn(Either.right(getUserWithAllFieldsToAdd()));


        CreateUserCmd createUserCmd = userAggregate.handle(getCreateUserCmd());

        assertFalse(createUserCmd.hasErrors());

    }

    @Test
    public void userStoreNoOkBecauseDataBaseProblemWithGenerateUserId(){
        when(userRepository.getUserId())
                .thenReturn(Either.left(new DatabaseException("There is problem with Database")));
        when(userRepository.getUserUuid()).thenReturn(Either.right(new UserUuid("1-2-3-4-5-6")));
        when(userRepository.addUser(any(User.class))).thenReturn(Either.right(getUserWithAllFieldsToAdd()));


        CreateUserCmd createUserCmd = userAggregate.handle(getCreateUserCmd());

        assertTrue(createUserCmd.hasErrors());
        DatabaseException exception = (DatabaseException) createUserCmd.getErrors().stream().findFirst().get();
        assertEquals("There is problem with Database", exception.getMessage());
    }

    @Test
    public void userStoreNoOkBecauseDataBaseProblemWithGenerateUserUuiId(){
        when(userRepository.getUserId()).thenReturn(Either.right(new UserId(1L)));
        when(userRepository.getUserUuid())
                .thenReturn(Either.left(new DatabaseException("There is problem with Database")));
        when(userRepository.addUser(any(User.class))).thenReturn(Either.right(getUserWithAllFieldsToAdd()));


        CreateUserCmd createUserCmd = userAggregate.handle(getCreateUserCmd());

        assertTrue(createUserCmd.hasErrors());
        DatabaseException exception = (DatabaseException) createUserCmd.getErrors().stream().findFirst().get();
        assertEquals("There is problem with Database", exception.getMessage());
    }

    /*@Test
    public void userStoreNoOkBecauseThereIsDomainErrors(){
        when(userRepository.getUserId()).thenReturn(Either.right(new UserId(1L)));
        when(userRepository.getUserUuid()).thenReturn(Either.right(new UserUuid("1-2-3-4-5-6")));
        //when(userRepository.addUser(any(User.class))).thenReturn(Either.right(getUserWithUserPasswordNullAndInvalidEmail()));


        CreateUserCommand createUserCmd = userAggregate.handle(getCreateUserCmdWithFailEmailAndNullPassword());

        assertTrue(createUserCmd.hasErrors());
        //DatabaseException exception = (DatabaseException) createUserCmd.getErrors().stream().findFirst().get();
        //assertEquals("There is problem with Database", exception.getMessage());
    }*/

    @Test
    public void userStoreNoOkBecauseDataBaseProblemWithAddUser(){
        when(userRepository.getUserId()).thenReturn(Either.right(new UserId(1L)));
        when(userRepository.getUserUuid()).thenReturn(Either.right(new UserUuid("1-2-3-4-5-6")));
        when(userRepository.addUser(any(User.class)))
                .thenReturn(Either.left(new DatabaseException("There is problem with Database")));

        CreateUserCmd createUserCmd = userAggregate.handle(getCreateUserCmd());

        assertTrue(createUserCmd.hasErrors());
        DatabaseException exception = (DatabaseException) createUserCmd.getErrors().stream().findFirst().get();
        assertEquals("There is problem with Database", exception.getMessage());
    }
}
