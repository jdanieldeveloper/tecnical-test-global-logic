package com.global.logic.user.command.infrastructure.persistence.dao;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.persistence.mybatis.mapper.PartyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static com.global.logic.user.command.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorCreateRole;
import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;


/**
 * Created by daniel.carvajal
 *
 **/
@SpringBootTest
@ContextConfiguration(classes = UserManagerTestConfig.class)
public class PartyDaoTest {

    @MockBean
    private PartyMapper partyMapper;

    @SpyBean
    private PartyDao partyDao;

    @Test
    public void nexValueForIdentifierGenerateOk()  {
        when(partyMapper.nexValueForIdentifier()).thenReturn(1L);

        long identifier = partyDao.nexValueForIdentifier();
        assertTrue(identifier > 0);
    }

    @Test
    public void nexValueForIdentifierNotGenerateBecauseThereIsInconsistency() {
        when(partyMapper.nexValueForIdentifier()).thenReturn(-1L);

        DatabaseException exception =
                assertThrows(DatabaseException.class, () -> partyDao.nexValueForIdentifier());

        assertEquals(exception.getClass(), DatabaseException.class);
        assertEquals(exception.getMessage(), "Error partyId can be larger than of 0!!!");
    }

    @Test
    public void nexValueForIdentifierNotGenerateBecauseThrowException() {
        when(partyMapper.nexValueForIdentifier()).thenThrow(new RuntimeException("Error in DataBase!!!"));

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> partyDao.nexValueForIdentifier());

        assertEquals(exception.getClass(), RuntimeException.class);
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

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> partyDao.saveUserLogin(partyDto));

        assertEquals(exception.getClass(), RuntimeException.class);
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

        IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> partyDao.saveUserRole(roleCreate));

        assertEquals(exception.getClass(), IllegalStateException.class);
        assertEquals(exception.getMessage(), "Error row affected more than 1 when user role was created!!!");
    }

    @Test
    public void saveUserRoleNotCreatedBecauseThrowException() {
        when(partyMapper.saveUserRole(any(UserRoleDto.class))).thenThrow(new RuntimeException("Error in DataBase!!!"));

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> partyDao.saveUserRole(roleCreate));

        assertEquals(exception.getClass(), RuntimeException.class);
        assertEquals(exception.getMessage(), "Error in DataBase!!!");
    }
}
