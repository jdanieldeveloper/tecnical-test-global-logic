package com.global.logic.user.command.infrastructure.fixture;

import com.global.logic.user.command.domain.user.Phone;
import com.global.logic.user.command.infrastructure.api.model.CreateUserReq;

import java.util.Set;

public class UserModelFixture {

    public static CreateUserReq getCreateUserReqWillAllOkFields() {
        return CreateUserReq.builder()
                .name("Daniel Carvajal")
                .email("dcarvajal3@gmail.com")
                .password("123456789")
                .phones(Set.of(new Phone("56", 9, 12345678L)))
                .build();
    }
}
