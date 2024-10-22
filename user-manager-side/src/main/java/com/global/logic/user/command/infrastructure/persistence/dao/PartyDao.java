package com.global.logic.user.command.infrastructure.persistence.dao;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;

import java.util.List;

/**
 *
 *
 * @author daniel.carvajal
 */
public interface PartyDao extends Dao {

    Long nexValueForIdentifier();

    PartyDto saveParty(PartyDto partyDto);

    PartyDto saveUserLogin(PartyDto partyDto);

    UserRoleDto saveUserRole(UserRoleDto userRoleDto);

    PartyDto saveUserWithRoles(PartyDto partyDto);

    PartyDto findPartyByUserLoginId(String userLoginId);

    List<UserRoleDto> findRoleByUserLoginId(String userLoginId);

}
