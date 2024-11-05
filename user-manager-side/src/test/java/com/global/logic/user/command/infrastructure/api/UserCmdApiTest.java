package com.global.logic.user.command.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.exception.BusinessException;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.exception.DomainException;
import com.global.logic.user.command.infrastructure.persistence.mybatis.mapper.PartyMapper;
import com.global.logic.user.query.infraestructure.exception.UserAuthenticationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsSavedWithDifferentPassEncrypted;
import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsSavedWithPassEncrypted;
import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by daniel.carvajal
 **/
@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserManagerTestConfig.class})
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
        when(partyMapper.nextValueForIdentifier()).thenReturn(1L);
        when(partyMapper.saveParty(any())).thenReturn(1);
        when(partyMapper.saveUserLogin(any())).thenReturn(1);
        when(partyMapper.saveUserRole(any())).thenReturn(1);
        when(partyMapper.saveUserContact(any())).thenReturn(1);
        when(partyMapper.saveUserPhone(any())).thenReturn(1);
        when(partyMapper.findPartyByUserLoginId(any()))
                .thenReturn(null)
                .thenReturn(getPartyDtoWithAllFieldsSavedWithPassEncrypted());
        when(partyMapper.findRoleByUserLoginId(any()))
                .thenReturn(getPartyDtoWithAllFieldsSavedWithPassEncrypted().getUserRolesDtos());
        //
        mockMvc.perform(post("/api/v1/command/users/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.created").exists())
                .andExpect(jsonPath("$.lastLogin").exists())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.isActive").exists());

        // verify
        verify(partyMapper, times(1)).nextValueForIdentifier();
        verify(partyMapper, times(1)).saveParty(any());
        verify(partyMapper, times(1)).saveUserLogin(any());
        // save 2 roles for default
        verify(partyMapper, times(2)).saveUserRole(any());
        verify(partyMapper, times(2)).saveUserContact(any());
        verify(partyMapper, times(2)).saveUserPhone(any());
        // call when find user and auth user
        verify(partyMapper, times(2)).findPartyByUserLoginId(any());
        verify(partyMapper, times(1)).findRoleByUserLoginId(any());
    }

    @Test
    public void noCreateUserAndGetHttp409ConflictBecauseUserExists() throws Exception {
        // setup
        when(partyMapper.findPartyByUserLoginId(any()))
                .thenReturn(getPartyDtoWithAllFieldsSavedWithPassEncrypted());
        //
        mockMvc.perform(post("/api/v1/command/users/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].codigo").value(BusinessException.getErrorCode()))
                .andExpect(jsonPath("$.errors[0].detail")
                .value("User already exists in the system!!!"));

        //verify
        verify(partyMapper, times(1)).findPartyByUserLoginId(any());
    }

    @Test
    public void noCreateUserAndGetHttp400OBadRequestBecauseFormatEmailInvalid() throws Exception {
        //
        mockMvc.perform(post("/api/v1/command/users/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqBadRequestEmailInvalid())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].codigo").value(DomainException.getErrorCode()))
                .andExpect(jsonPath("$.errors[0].detail")
                        .value("Error validation [email : The email have a illegal format]"));
    }

    @Test
    public void noCreateUserAndGetHttp400OBadRequestBecauseFormatPasswordInvalid() throws Exception {
        //
        mockMvc.perform(post("/api/v1/command/users/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqBadRequestPasswordInvalid())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].codigo").value(DomainException.getErrorCode()))
                .andExpect(jsonPath("$.errors[0].detail")
                        .value("Error validation [password : The password have a illegal format]"));
    }

    @Test
    public void noCreateUserBecauseGetHttp500InternalServerError() throws Exception {
        // setup
        when(partyMapper.nextValueForIdentifier()).thenThrow(new DatabaseException("There is Database problem"));
        //
        mockMvc.perform(post("/api/v1/command/users/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].codigo").value(DatabaseException.getErrorCode()))
                .andExpect(jsonPath("$.errors[0].detail").value("There is Database problem"));

        // verify
        verify(partyMapper, times(1)).nextValueForIdentifier();
    }

    @Test
    public void noCreateUserBecauseGetHttp401UnauthorizedError() throws Exception {
        //setup
        when(partyMapper.nextValueForIdentifier()).thenReturn(1L);
        when(partyMapper.saveParty(any())).thenReturn(1);
        when(partyMapper.saveUserLogin(any())).thenReturn(1);
        when(partyMapper.saveUserRole(any())).thenReturn(1);
        when(partyMapper.saveUserContact(any())).thenReturn(1);
        when(partyMapper.saveUserPhone(any())).thenReturn(1);
        when(partyMapper.findPartyByUserLoginId(any()))
                .thenReturn(null)
                .thenReturn(getPartyDtoWithAllFieldsSavedWithDifferentPassEncrypted());
        when(partyMapper.findRoleByUserLoginId(any()))
                .thenReturn(getPartyDtoWithAllFieldsSavedWithDifferentPassEncrypted().getUserRolesDtos());

        //
        mockMvc.perform(post("/api/v1/command/users/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].codigo").value(UserAuthenticationException.getErrorCode()))
                .andExpect(jsonPath("$.errors[0].detail").value("Bad credentials"));

        // verify
        verify(partyMapper, times(1)).nextValueForIdentifier();
        verify(partyMapper, times(1)).saveParty(any());
        verify(partyMapper, times(1)).saveUserLogin(any());
        // save 2 roles for default
        verify(partyMapper, times(2)).saveUserRole(any());
        verify(partyMapper, times(2)).saveUserContact(any());
        verify(partyMapper, times(2)).saveUserPhone(any());
        // call when find user and auth user
        verify(partyMapper, times(2)).findPartyByUserLoginId(any());
        verify(partyMapper, times(1)).findRoleByUserLoginId(any());

    }
}
