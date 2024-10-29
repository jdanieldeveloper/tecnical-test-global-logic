package com.global.logic.user.command.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.getCreateUserReqWillAllOkFields;


/**
 * Created by daniel.carvajal
 **/
@AutoConfigureMockMvc
@ContextConfiguration(classes = UserManagerTestConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCmdApiTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void createUser() throws Exception {
        //
        mockMvc.perform(post("/api/command/user/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
