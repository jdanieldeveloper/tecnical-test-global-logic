package com.global.logic.user.query.application.service;

import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsToSave;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * Created by daniel.carvajal
 *
 **/


@SpringBootTest
@ContextConfiguration(classes = { UserManagerTestConfig.class})
public class UserDetailsServiceTest {

    @MockBean
    private PartyDao partyDao;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final PartyDto partyDto = getPartyDtoWithAllFieldsToSave();


    @Test
    public void loadUserByUsernameOk()  {
        // setup
        when(partyDao.findPartyByUserLoginId(any())).thenReturn(partyDto);
        //
        UserDetails userDetails = userDetailsService.loadUserByUsername(partyDto.getUserLoginId());
        assertEquals(userDetails.getUsername(), partyDto.getUserLoginId());
    }

    @Test
    public void loadUserByUsernameNOkBecauseUserNameNotFound()  {
        // setup
        when(partyDao.findPartyByUserLoginId(any()))
                .thenThrow(new UserNotFoundException("The party by user login wasn't found!!!"));
        //
        UsernameNotFoundException exception =
                assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("fail.mock@gmail.com"));

        assertEquals(exception.getClass(), UsernameNotFoundException.class);
        assertEquals(exception.getMessage(), "The party by user login wasn't found!!!");

    }
}
