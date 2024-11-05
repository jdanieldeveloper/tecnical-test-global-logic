package com.global.logic.user.command.infrastructure.persistence.dao;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;

import java.util.List;

/**
 *
 *
 * @author daniel.carvajal
 */
public interface PartyDao extends Dao {

    Long nextValueForIdentifier();

    Long nextValueForContact();

    PartyDto saveParty(PartyDto partyDto);

    PartyDto saveUserLogin(PartyDto partyDto);

    UserRoleDto saveUserRole(UserRoleDto userRoleDto);

    PartyDto saveUser(PartyDto partyDto);

    UserPhoneDto saveUserContact(UserPhoneDto userPhoneDto);

    UserPhoneDto saveUserPhone(UserPhoneDto userPhoneDto);

    PartyDto findPartyByUserLoginId(String userLoginId);

    List<UserRoleDto> findRoleByUserLoginId(String userLoginId);

    List<UserPhoneDto> findPhoneByUserLoginId(String userLoginId);


}
