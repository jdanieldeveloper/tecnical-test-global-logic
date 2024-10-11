package com.global.logic.user.manager.infrastructure.fixture;

import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
import com.global.logic.user.manager.infrastructure.enums.RoleEnum;

public class UserRoleFixture {

    public static UserRoleDto getUserRoleDtoWithVisitorCreateRole() {
        return
                UserRoleDto.builder()
                        .partyId(1L)
                        .roleTypeId(RoleEnum.VISITOR_CREATE.getRoleId())
                        .description(RoleEnum.VISITOR_CREATE.getDescription())
                        .build();
    }

    public static UserRoleDto getUserRoleDtoWithVisitorUpdateRole() {
        return
                UserRoleDto.builder()
                        .partyId(1L)
                        .roleTypeId(RoleEnum.VISITOR_UPDATE.getRoleId())
                        .description(RoleEnum.VISITOR_UPDATE.getDescription())
                        .build();
    }
}
