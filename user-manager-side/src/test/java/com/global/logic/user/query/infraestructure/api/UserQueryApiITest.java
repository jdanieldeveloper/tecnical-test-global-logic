package com.global.logic.user.query.infraestructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.logic.user.command.infrastructure.api.model.CreateUserResp;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.config.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.getCreateUserReqWillAllOkFields;
import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.loginUserReqWillAllOkFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by daniel.carvajal
 **/
@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserManagerTestConfig.class, WebSecurityConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserQueryApiITest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUserAndAfterLoginGetHttp200Ok() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/command/user/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        // get response with generated token
        CreateUserResp reponse =
                objectMapper.readValue(result.getResponse().getContentAsString(), CreateUserResp.class);

        //
        mockMvc.perform(post("/api/query/user/login")
                        .header("Authorization", "Bearer " + reponse.getToken())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
        //.andExpect(jsonPath("$.id").value(1)) // we could find into json

    }
}
