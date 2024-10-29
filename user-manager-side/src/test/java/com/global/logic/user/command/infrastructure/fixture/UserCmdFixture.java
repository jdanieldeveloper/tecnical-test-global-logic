package com.global.logic.user.command.infrastructure.fixture;

import com.global.logic.user.command.application.cmd.CreateUserCmd;
import com.global.logic.user.command.domain.user.*;

import java.util.Set;

public class UserCmdFixture {

    public static CreateUserCmd getCreateUserCmd() {
        return CreateUserCmd.builder()
                .name("Daniel Carvajal")
                .email("dcarvajal3@gmail.com")
                .password("123456789")
                .phones(Set.of(new Phone("56", 9, 12345678L)))
                .build();
    }

    public static CreateUserCmd getCreateUserCmdWithFailEmailAndNullPassword() {
        return CreateUserCmd.builder()
                .name("Daniel Carvajal")
                .email("dcarvajal3@g")
                .password(null) // fail pass
                .phones(Set.of(new Phone("56", 9, 12345678L)))
                .build();
    }
}
