package com.global.logic.user.command.infrastructure.persistence.dao;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.persistence.mybatis.mapper.PartyMapper;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsSavedWithPassEncrypted;
import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;
import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithOutRoles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static com.global.logic.user.command.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorCreateRole;


/**
 * Created by daniel.carvajal
 *
 **/
@SpringBootTest
@ContextConfiguration(classes = UserManagerTestConfig.class)
public class PartyDaoTest {

    @MockBean
    private PartyMapper partyMapper;

    @Autowired
    private PartyDao partyDao;

    @Test
    public void nextValueForIdentifierGenerateOk()  {
        when(partyMapper.nextValueForIdentifier()).thenReturn(1L);

        long identifier = partyDao.nextValueForIdentifier();
        assertTrue(identifier > 0);
    }

    @Test
    public void nextValueForIdentifierNotGenerateBecauseThereIsInconsistency() {
        when(partyMapper.nextValueForIdentifier()).thenReturn(-1L);

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.nextValueForIdentifier());

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error partyId can be larger than of 0!!!");
    }

    @Test
    public void nextValueForIdentifierNotGenerateBecauseThrowException() {
        when(partyMapper.nextValueForIdentifier()).thenThrow(new RuntimeException("Error in DataBase!!!"));

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.nextValueForIdentifier());

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error in DataBase!!!");
    }

    @Test
    public void savePartyOk()  {
        when(partyMapper.saveParty(any(PartyDto.class))).thenReturn(1);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();

        PartyDto partySaved = partyDao.saveParty(partyDto);
        assertNotNull(partySaved);
    }

    @Test
    public void savePartyNotCreatedBecauseThereIsInconsistency()  {
        when(partyMapper.saveParty(any(PartyDto.class))).thenReturn(2);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.saveParty(partyDto));

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error row affected more than 1 when party was created!!!");
    }

    @Test
    public void savePartyNotCreatedBecauseThrowException()  {
        when(partyMapper.saveParty(any(PartyDto.class))).thenThrow(new RuntimeException("Error in DataBase!!!"));

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.saveParty(partyDto));

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error in DataBase!!!");

    }

    @Test
    public void saveUserLoginOk()  {
        when(partyMapper.saveUserLogin(any(PartyDto.class))).thenReturn(1);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();

        PartyDto partySaved = partyDao.saveUserLogin(partyDto);
        assertNotNull(partySaved);
    }

    @Test
    public void saveUserLoginNotCreatedBecauseThereIsInconsistency()  {
        when(partyMapper.saveUserLogin(any(PartyDto.class))).thenReturn(2);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.saveUserLogin(partyDto));

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error row affected more than 1 when user was created!!!");
    }

    @Test
    public void saveUserLoginNotCreatedBecauseThrowException()  {
        when(partyMapper.saveUserLogin(any(PartyDto.class))).thenThrow(new RuntimeException("Error in DataBase!!!"));

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.saveUserLogin(partyDto));

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error in DataBase!!!");
    }

    @Test
    public void saveUserRoleOk()  {
        when(partyMapper.saveUserRole(any(UserRoleDto.class))).thenReturn(1);

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();
        UserRoleDto roleSaved = partyDao.saveUserRole(roleCreate);
        assertNotNull(roleSaved);
    }

    @Test
    public void saveUserRoleNotCreatedBecauseThereIsInconsistency()  {
        when(partyMapper.saveUserRole(any(UserRoleDto.class))).thenReturn(2);

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.saveUserRole(roleCreate));

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error row affected more than 1 when user role was created!!!");
    }

    @Test
    public void saveUserRoleNotCreatedBecauseThrowException() {
        when(partyMapper.saveUserRole(any(UserRoleDto.class))).thenThrow(new RuntimeException("Error in DataBase!!!"));

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.saveUserRole(roleCreate));

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error in DataBase!!!");
    }

    @Test
    public void saveUserOk()  {
        // setup
        when(partyMapper.nextValueForIdentifier()).thenReturn(1L);
        when(partyMapper.saveParty(any())).thenReturn(1);
        when(partyMapper.saveUserLogin(any())).thenReturn(1);
        when(partyMapper.saveUserRole(any())).thenReturn(1);
        when(partyMapper.saveUserContact(any())).thenReturn(1);
        when(partyMapper.saveUserPhone(any())).thenReturn(1);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();

        PartyDto partySaved = partyDao.saveUser(partyDto);
        assertNotNull(partySaved);
    }

    @Test
    public void findPartyByUserLoginIdOk()  {
        PartyDto partySaved = getPartyDtoWithAllFieldsSavedWithPassEncrypted();
        // setup
        when(partyMapper.findPartyByUserLoginId(any())).thenReturn(partySaved);

        PartyDto partyFound = partyDao.findPartyByUserLoginId(partySaved.getUserLoginId());

        assertEquals(partySaved.getUserLoginId(), partyFound.getUserLoginId());
        assertEquals(partySaved.getPartyId(), partyFound.getPartyId());
        assertEquals(partySaved.getPartyUuid(), partyFound.getPartyUuid());
    }

    @Test
    public void findPartyByUserLoginIdNOkBecauseUserNotFound()  {
        PartyDto partySaved = getPartyDtoWithAllFieldsSavedWithPassEncrypted();
        // setup
        when(partyMapper.findPartyByUserLoginId(any())).thenReturn(null);

        UserNotFoundException exception =
                assertThrows(UserNotFoundException.class, () -> partyDao.findPartyByUserLoginId(partySaved.getUserLoginId()));

        assertEquals(exception.getClass(), UserNotFoundException.class);
        assertEquals(exception.getMessage(), "The party by user login wasn't found!!!");
    }

    @Test
    public void findRoleByUserLoginIdOk()  {
        PartyDto partySaved = getPartyDtoWithAllFieldsSavedWithPassEncrypted();
        // setup
        when(partyMapper.findRoleByUserLoginId(any())).thenReturn(partySaved.getUserRolesDtos());

        List<UserRoleDto> userRolesFound = partyDao.findRoleByUserLoginId(partySaved.getUserLoginId());

        assertFalse(userRolesFound.isEmpty());
        assertEquals(2, userRolesFound.size());
    }

    @Test
    public void findRoleByUserLoginIdNOkBecauseRolesNotFound()  {
        PartyDto partySaved = getPartyDtoWithOutRoles();
        // setup
        when(partyMapper.findRoleByUserLoginId(any())).thenReturn(partySaved.getUserRolesDtos());

        List<UserRoleDto> userRolesFound = partyDao.findRoleByUserLoginId(partySaved.getUserLoginId());

        assertTrue(userRolesFound.isEmpty());
    }

    @Test
    public void findRoleByUserLoginIdNOkBecauseExceptionWhenFindRoles()  {
        PartyDto partySaved = getPartyDtoWithAllFieldsSavedWithPassEncrypted();
        // setup
        when(partyMapper.findRoleByUserLoginId(any())).thenThrow(new RuntimeException("Problem to find roles"));

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.findRoleByUserLoginId(partySaved.getUserLoginId()));

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Problem to find roles");
    }
}
