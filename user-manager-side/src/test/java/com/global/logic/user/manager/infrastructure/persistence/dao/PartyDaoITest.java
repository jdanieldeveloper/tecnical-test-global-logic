package com.global.logic.user.manager.infrastructure.persistence.dao;

import com.global.logic.user.manager.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.logic.user.manager.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorCreateRole;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.global.logic.user.manager.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;
import static com.global.logic.user.manager.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorUpdateRole;


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

        boolean isSavedUser = partyDao.saveUserLogin(partyDto);
        assertTrue(isSavedUser);

        boolean isSaved = partyDao.saveParty(partyDto);
        assertTrue(isSaved);

    }

    @Test
    public void saveUserLoginWithRoles()  {
        //
        long partyId = partyDao.nexValueForIdentifier();
        assertTrue(partyId > 0);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();
        partyDto.setPartyId(partyId);

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        assertTrue(isSaved);

    }

    @Test
    public void saveUserLoginAndFindUser() {
        //
        long partyId = partyDao.nexValueForIdentifier();
        assertTrue(partyId > 0);

        PartyDto partyDto = getPartyDtoWithAllFieldsToSave();
        partyDto.setPartyId(partyId);

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        assertTrue(isSaved);

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

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        assertTrue(isSaved);

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();
        roleCreate.setPartyId(partyId);
        boolean isSavedRole = partyDao.saveUserRole(roleCreate);
        assertTrue(isSavedRole);

        UserRoleDto roleUpdate = getUserRoleDtoWithVisitorUpdateRole();
        roleUpdate.setPartyId(partyId);
        isSavedRole = partyDao.saveUserRole(roleUpdate);
        assertTrue(isSavedRole);

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

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        assertTrue(isSaved);

        UserRoleDto roleCreate = getUserRoleDtoWithVisitorCreateRole();
        roleCreate.setPartyId(partyId);
        boolean isSavedRole = partyDao.saveUserRole(roleCreate);
        assertTrue(isSavedRole);

        UserRoleDto roleUpdate = getUserRoleDtoWithVisitorUpdateRole();
        roleUpdate.setPartyId(partyId);
        isSavedRole = partyDao.saveUserRole(roleUpdate);
        assertTrue(isSavedRole);

        PartyDto partyDtoFind = partyDao.findPartyByUserLoginId(partyDto.getUserLoginId());
        assertEquals(partyDto.getUserLoginId(), partyDtoFind.getUserLoginId());
        //
        List<UserRoleDto> userRoleDtos = partyDao.findRoleByUserLoginId(partyDto.getUserLoginId());
        assertEquals(userRoleDtos.size(), 2);

    }
}
