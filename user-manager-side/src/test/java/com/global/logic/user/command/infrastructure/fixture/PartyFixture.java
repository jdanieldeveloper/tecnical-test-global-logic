package com.global.logic.user.command.infrastructure.fixture;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.enums.UserStatusEnum;
import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.global.logic.user.command.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorCreateRole;
import static com.global.logic.user.command.infrastructure.fixture.UserRoleFixture.getUserRoleDtoWithVisitorUpdateRole;
import static com.global.logic.user.command.infrastructure.fixture.UserPhoneFixture.getUserContactPhoneDto1;
import static com.global.logic.user.command.infrastructure.fixture.UserPhoneFixture.getUserContactPhoneDto2;

public class PartyFixture {

    public static PartyDto getPartyDtoWithAllFieldsToSave() {
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
                .currentPassword("m1Passw2rd")
                //
                .userRolesDtos(List.of(
                        getUserRoleDtoWithVisitorCreateRole(),
                        getUserRoleDtoWithVisitorUpdateRole()))
                .userPhonesDtos(List.of(
                        getUserContactPhoneDto1(),
                        getUserContactPhoneDto2()
                ))
                .build();
    }

    public static PartyDto getPartyDtoWithAllFieldsSavedWithPassEncrypted() {
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
                .currentPassword(new BCryptPasswordEncoder().encode("m1Passw2rd"))
                //
                .userRolesDtos(List.of(
                        getUserRoleDtoWithVisitorCreateRole(),
                        getUserRoleDtoWithVisitorUpdateRole()))
                .userPhonesDtos(List.of(
                        getUserContactPhoneDto1(),
                        getUserContactPhoneDto2()
                ))
                .build();
    }

    public static PartyDto getPartyDtoWithAllFieldsSavedWithDifferentPassEncrypted() {
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
                .currentPassword(new BCryptPasswordEncoder().encode("mockfailpass:-("))
                //
                .userRolesDtos(List.of(
                        getUserRoleDtoWithVisitorCreateRole(),
                        getUserRoleDtoWithVisitorUpdateRole()))
                .userPhonesDtos(List.of(
                        getUserContactPhoneDto1(),
                        getUserContactPhoneDto2()
                ))
                .build();
    }

    public static PartyDto getPartyDtoWithOutRoles() {
        return PartyDto.builder()
                .partyId(1L)
                .partyUuid(UUID.randomUUID().toString())
                .partyType(UserTypeEnum.VISITOR.getTypeId())
                .partyName("Daniel Carvajal")
                .description("Description Mock")
                .statusId(UserStatusEnum.ENABLED.getStatusId())
                //
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .lastLoginDate(LocalDateTime.now())
                .lastModifiedByUserLogin("dcarvajal3@gmail.com")
                //
                .userLoginId("dcarvajal3@gmail.com")
                .currentPassword(new BCryptPasswordEncoder().encode("m1Passw2rd"))
                .userPhonesDtos(List.of(
                        getUserContactPhoneDto1(),
                        getUserContactPhoneDto2()
                ))
                .build();
    }
}
