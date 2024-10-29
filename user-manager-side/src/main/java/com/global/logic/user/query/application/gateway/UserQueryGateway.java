package com.global.logic.user.query.application.gateway;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.enums.UserStatusEnum;
import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserQueryGateway {

    public Either<UserNotFoundException, PartyDto> getUserByUuid(String uuid) {
        return Either.right(getMockPartyDtoAllFiels());
    }

    private PartyDto getMockPartyDtoAllFiels() {
        return PartyDto.builder()
                .partyId(1L)
                .partyUuid(UUID.randomUUID().toString())
                .partyType(UserTypeEnum.VISITOR.getTypeId())
                .partyName("Daniel Carvajal")
                .description("Description Mock")
                .statusId(UserStatusEnum.ENABLED.getStatusId())
                //
                .createdDate(LocalDateTime.now())
                .createByUserLogin("dcarvajal3@gmail.com")
                .lastLoginDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .lastModifiedByUserLogin("dcarvajal3@gmail.com")
                //
                .userLoginId("dcarvajal3@gmail.com")
                .currentPassword("123456789")
                .build();
    }
}
