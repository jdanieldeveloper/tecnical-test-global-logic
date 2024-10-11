package com.global.logic.user.manager.infrastructure.fixture;

import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.enums.UserStatusEnum;
import com.global.logic.user.manager.infrastructure.enums.UserTypeEnum;

import java.util.Date;

public class PartyFixture {

    public static PartyDto getPartyDtoWithAllFieldsToSave() {
        return PartyDto.builder()
                .partyId(1L)
                .partyType(UserTypeEnum.VISITOR.getTypeId())
                .description("Description Mock")
                .statusId(UserStatusEnum.ENABLED.getStatusId())
                //
                .createdDate(new Date())
                .createByUserLogin("dcarvajal3")
                .lastModifiedDate(new Date())
                .lastModifiedByUserLogin("dcarvajal3")
                //
                .userLoginId("dcarvajal3")
                .currentPassword("123456789")
                .build();
    }
}
