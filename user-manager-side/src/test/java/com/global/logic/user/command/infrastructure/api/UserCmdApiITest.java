package com.global.logic.user.command.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.logic.user.command.infrastructure.config.UserManagerTestConfig;
import com.global.logic.user.command.infrastructure.config.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static com.global.logic.user.command.infrastructure.fixture.UserModelFixture.getCreateUserReqWillAllOkFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by daniel.carvajal
 **/
@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserManagerTestConfig.class, WebSecurityConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCmdApiITest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUserAndGetHttp200Ok() throws Exception {
        //
        mockMvc.perform(post("/api/command/user/sign-up")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getCreateUserReqWillAllOkFields())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
                //.andExpect(jsonPath("$.id").value(1)) // we could find into json

    }
}
