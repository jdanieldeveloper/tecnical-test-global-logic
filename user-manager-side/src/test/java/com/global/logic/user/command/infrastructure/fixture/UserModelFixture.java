package com.global.logic.user.command.infrastructure.fixture;

import com.global.logic.user.command.infrastructure.api.model.CreateUserReq;
import com.global.logic.user.command.infrastructure.api.model.PhoneModel;
import com.global.logic.user.query.infraestructure.api.model.LoginModelReq;

import java.util.List;

public class UserModelFixture {

    public static CreateUserReq getCreateUserReqWillAllOkFields() {
        return CreateUserReq.builder()
                .name("Daniel Carvajal")
                .email("dcarvajal3@gmail.com")
                .password("m1Passw2rd")
                .phones(List.of(
                        PhoneModel.builder()
                                .countryCode("56")
                                .cityCode(9)
                                .number(12345678)
                                .build(),
                        PhoneModel.builder()
                                .countryCode("56")
                                .cityCode(9)
                                .number(87654321L)
                                .build()))
                .build();
    }

    public static CreateUserReq getCreateUserReqBadRequestEmailInvalid() {
        return CreateUserReq.builder()
                .name("Daniel Carvajal")
                .email("dcarva") // without domain
                .password("m1Passw2rd")
                .phones(List.of(
                        PhoneModel.builder()
                                .countryCode("56")
                                .cityCode(9)
                                .number(12345678)
                                .build(),
                        PhoneModel.builder()
                                .countryCode("56")
                                .cityCode(9)
                                .number(87654321L)
                                .build()))
                .build();
    }

    public static CreateUserReq getCreateUserReqBadRequestPasswordInvalid() {
        return CreateUserReq.builder()
                .name("Daniel Carvajal")
                .email("dcarvajal3@gmail.com")
                .password("123456789101112") // larger than 12 and format incorrectly
                .phones(List.of(
                        PhoneModel.builder()
                                .countryCode("56")
                                .cityCode(9)
                                .number(12345678)
                                .build(),
                        PhoneModel.builder()
                                .countryCode("56")
                                .cityCode(9)
                                .number(87654321L)
                                .build()))
                .build();
    }

    public static LoginModelReq loginUserReqWillAllOkFields() {
        return LoginModelReq.builder()
                .email("dcarvajal3@gmail.com")
                .password("m1Passw2rd")
                .build();
    }
}
