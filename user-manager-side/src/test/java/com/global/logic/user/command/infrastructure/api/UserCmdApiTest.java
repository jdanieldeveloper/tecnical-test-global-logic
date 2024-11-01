package com.global.logic.user.command.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.config.WebSecurityConfig;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.persistence.mybatis.mapper.PartyMapper;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsSavedWithPassEncrypted;
import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.getCreateUserReqWillAllOkFields;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by daniel.carvajal
 **/
@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserManagerTestConfig.class, WebSecurityConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCmdApiTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PartyMapper partyMapper;


    @Test
    public void createUserAndGetHttp200Ok() throws Exception {
        // setup
        when(partyMapper.nexValueForIdentifier()).thenReturn(1L);
        when(partyMapper.saveParty(any())).thenReturn(1);
        when(partyMapper.saveUserLogin(any())).thenReturn(1);
        when(partyMapper.saveUserRole(any())).thenReturn(1);
        when(partyMapper.findPartyByUserLoginId(any()))
                .thenReturn(getPartyDtoWithAllFieldsSavedWithPassEncrypted());
        when(partyMapper.findRoleByUserLoginId(any()))
                .thenReturn(getPartyDtoWithAllFieldsSavedWithPassEncrypted().getUserRolesDtos());
        //
        mockMvc.perform(post("/api/command/user/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
                //.andExpect(jsonPath("$.id").value(1)) // we could find into json
    }

    @Test
    public void noCreateUserBecauseGetHttp500InternalServerError() throws Exception {
        // setup
        when(partyMapper.nexValueForIdentifier()).thenThrow(new DatabaseException("There is Database problem"));
        //
        mockMvc.perform(post("/api/command/user/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].codigo").value(DatabaseException.getErrorCode()))
                .andExpect(jsonPath("$.errors[0].detail").value("There is Database problem"));
    }

    @Test
    public void noCreateUserBecauseGetHttp401UnauthorizedError() throws Exception {
        //setup
        when(partyMapper.nexValueForIdentifier()).thenReturn(1L);
        when(partyMapper.saveParty(any())).thenReturn(1);
        when(partyMapper.saveUserLogin(any())).thenReturn(1);
        when(partyMapper.saveUserRole(any())).thenReturn(1);
        when(partyMapper.findPartyByUserLoginId(any())).thenThrow(new RuntimeException());

        //
        mockMvc.perform(post("/api/command/user/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].codigo").value(UserAuthenticationException.getErrorCode()))
                .andExpect(jsonPath("$.errors[0].detail").value("Bad credentials"));

    }
}
