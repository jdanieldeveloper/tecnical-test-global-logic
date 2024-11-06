package com.global.logic.user.query.infraestructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.logic.user.command.infrastructure.api.model.CreateUserResp;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.config.WebSecurityConfig;
import com.global.logic.user.command.infrastructure.persistence.mybatis.mapper.PartyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.global.logic.user.command.infrastructure.fixture.PartyFixture.getPartyDtoWithAllFieldsSavedWithPassEncrypted;
import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.getCreateUserReqWillAllOkFields;
import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.loginUserReqWillAllOkFields;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


/**
 * Created by daniel.carvajal
 **/
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {UserManagerTestConfig.class, WebSecurityConfig.class})
public class UserQueryApiITest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PartyMapper partyMapper;

    @Test
    public void createUserAndAfterLoginGetHttp200Ok() throws Exception {
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

        // generate token
        MvcResult result = mockMvc.perform(post("/api/v1/command/users/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        // get response with generated token
        CreateUserResp response =
                objectMapper.readValue(result.getResponse().getContentAsString(), CreateUserResp.class);

        // login user and auth by new token
        mockMvc.perform(post("/api/v1/query/users/login")
                        .header("Authorization", "Bearer " + response.getToken())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.created").exists())
                .andExpect(jsonPath("$.lastLogin").exists())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.isActive").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.phones").exists());


        // verify
        verify(partyMapper, times(1)).nextValueForIdentifier();
        verify(partyMapper, times(1)).saveParty(any());
        verify(partyMapper, times(1)).saveUserLogin(any());
        // save 2 roles for default
        verify(partyMapper, times(2)).saveUserRole(any());
        verify(partyMapper, times(2)).saveUserContact(any());
        verify(partyMapper, times(2)).saveUserPhone(any());
        // call when find user and auth user
        verify(partyMapper, times(4)).findPartyByUserLoginId(any());
        verify(partyMapper, times(3)).findRoleByUserLoginId(any());

    }

    //TODO validar el nombre y los fonos cuando son opcionales
}
