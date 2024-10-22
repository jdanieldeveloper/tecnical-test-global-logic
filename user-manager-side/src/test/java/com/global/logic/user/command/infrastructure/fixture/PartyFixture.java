package com.global.logic.user.command.infrastructure.fixture;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.enums.UserStatusEnum;
import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;

import java.time.LocalDateTime;

public class PartyFixture {

    public static PartyDto getPartyDtoWithAllFieldsToSave() {
        return PartyDto.builder()
                .partyId(1L)
                .partyType(UserTypeEnum.VISITOR.getTypeId())
                .partyName("Daniel Carvajal")
                .description("Description Mock")
                .statusId(UserStatusEnum.ENABLED.getStatusId())
                //
                .createdDate(LocalDateTime.now())
                .createByUserLogin("dcarvajal3@gmail.com")
                .lastModifiedDate(LocalDateTime.now())
                .lastModifiedByUserLogin("dcarvajal3@gmail.com")
                //
                .userLoginId("dcarvajal3@gmail.com")
                .currentPassword("123456789")
                .build();
    }
}
