package com.global.logic.user.query.infraestructure.service;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Created by daniel.carvajal
 *
 **/


@SpringBootTest
@ContextConfiguration(classes = UserManagerTestConfig.class)
@Transactional
public class UserDetailsServiceITest {

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final PartyDto partyDto = getPartyDtoWithAllFieldsToSave();


    @Test
    public void loadUserByUsernameOk()  {
        // setup
        partyDao.saveUserWithRoles(partyDto);
        //
        UserDetails userDetails = userDetailsService.loadUserByUsername(partyDto.getUserLoginId());
        assertEquals(userDetails.getUsername(), partyDto.getUserLoginId());
    }

    @Test
    public void loadUserByUsernameNOkBecauseUserNameNotFound()  {
        // setup
        partyDao.saveUserWithRoles(partyDto);
        //
        UsernameNotFoundException exception =
                assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("fail.mock@gmail.com"));

        assertEquals(exception.getClass(), UsernameNotFoundException.class);
        assertEquals(exception.getMessage(), "The party by user login wasn't found!!!");

    }
}
