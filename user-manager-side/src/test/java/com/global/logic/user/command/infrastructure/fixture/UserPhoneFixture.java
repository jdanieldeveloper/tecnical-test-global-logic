package com.global.logic.user.command.infrastructure.fixture;

import com.global.logic.user.command.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.command.infrastructure.enums.ContactEnum;

public class UserPhoneFixture {

    public static UserPhoneDto getUserContactPhoneDto1() {
        return
                UserPhoneDto.builder()
                        .partyId(1L)
                        .contactMechId(1L)
                        .contactMechTypeId(ContactEnum.TELECOM_NUMBER.getContactId())
                        .telecomNumberId(1L)
                        .countryCode("56")
                        .cityCode(9)
                        .contactNumber(12345678L)
                        .build();


    }

    public static UserPhoneDto getUserContactPhoneDto2() {
        return
                UserPhoneDto.builder()
                        .partyId(1L)
                        .contactMechId(2L)
                        .contactMechTypeId(ContactEnum.TELECOM_NUMBER.getContactId())
                        .telecomNumberId(2L)
                        .countryCode("56")
                        .cityCode(9)
                        .contactNumber(87654321L)
                        .build();
    }
}
