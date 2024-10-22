package com.global.logic.user.command.infrastructure.persistence.dao;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.logic.user.command.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorCreateRole;
import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;
import static com.global.logic.user.command.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorUpdateRole;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by daniel.carvajal
 *
 **/


@SpringBootTest
@ContextConfiguration(classes = UserManagerTestConfig.class)
@Transactional
public class PartyDaoITest {

    @Autowired
    private PartyDao partyDao;

    @Test
    public void nexValueForIdentifierOK()  {
        //
        long identifier = partyDao.nexValueForIdentifier();
        assertTrue(identifier > 0);
    }

    @Test
    public void saveUserLogin()  {
        //
        long partyId = partyDao.nexValueForIdentifier();
        assertTrue(partyId > 0);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();
        partyDto.setPartyId(partyId);

        partyDto = partyDao.saveUserLogin(partyDto);
        assertNotNull(partyDto);

        partyDto = partyDao.saveParty(partyDto);
        assertNotNull(partyDto);

    }

    @Test
    public void saveUserLoginWithRoles()  {
        //
        long partyId = partyDao.nexValueForIdentifier();
        assertTrue(partyId > 0);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();
        partyDto.setPartyId(partyId);

        partyDto = partyDao.saveUserLogin(partyDto);
        assertNotNull(partyDto);

        partyDto = partyDao.saveParty(partyDto);
        assertNotNull(partyDto);

    }

    @Test
    public void saveUserLoginAndFindUser() {
        //
        long partyId = partyDao.nexValueForIdentifier();
        assertTrue(partyId > 0);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();
        partyDto.setPartyId(partyId);

        partyDto = partyDao.saveUserLogin(partyDto);
        assertNotNull(partyDto);

        partyDto = partyDao.saveParty(partyDto);
        assertNotNull(partyDto);

        PartyDto partyDtoFind = partyDao.findPartyByUserLoginId(partyDto.getUserLoginId());
        assertEquals(partyDto.getUserLoginId(), partyDtoFind.getUserLoginId());

    }

    @Test
    public void saveUserLoginAndFindRoles() {
        //
        long partyId = partyDao.nexValueForIdentifier();
        assertTrue(partyId > 0);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();
        partyDto.setPartyId(partyId);

        partyDto = partyDao.saveUserLogin(partyDto);
        assertNotNull(partyDto);

        partyDto = partyDao.saveParty(partyDto);
        assertNotNull(partyDto);

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();
        roleCreate.setPartyId(partyId);
        roleCreate = partyDao.saveUserRole(roleCreate);
        assertNotNull(roleCreate);

        UserRoleDto roleUpdate = getUserRoleDtoWithVisitorUpdateRole();
        roleUpdate.setPartyId(partyId);
        roleUpdate = partyDao.saveUserRole(roleUpdate);
        assertNotNull(roleUpdate);

        List<UserRoleDto> userRoleDtos = partyDao.findRoleByUserLoginId(partyDto.getUserLoginId());
        assertEquals(userRoleDtos.size(), 2);
    }

    @Test
    public void saveUserLoginAndFindUserWithRoles() {
        //
        long partyId = partyDao.nexValueForIdentifier();
        assertTrue(partyId > 0);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();
        partyDto.setPartyId(partyId);

        partyDto = partyDao.saveUserLogin(partyDto);
        assertNotNull(partyDto);

        partyDto = partyDao.saveParty(partyDto);
        assertNotNull(partyDto);

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();
        roleCreate.setPartyId(partyId);
        roleCreate = partyDao.saveUserRole(roleCreate);
        assertNotNull(roleCreate);

        UserRoleDto roleUpdate = getUserRoleDtoWithVisitorUpdateRole();
        roleUpdate.setPartyId(partyId);
        roleUpdate = partyDao.saveUserRole(roleUpdate);
        assertNotNull(roleUpdate);

        PartyDto partyDtoFind = partyDao.findPartyByUserLoginId(partyDto.getUserLoginId());
        assertEquals(partyDto.getUserLoginId(), partyDtoFind.getUserLoginId());
        //
        List<UserRoleDto> userRoleDtos = partyDao.findRoleByUserLoginId(partyDto.getUserLoginId());
        assertEquals(userRoleDtos.size(), 2);

    }
}
