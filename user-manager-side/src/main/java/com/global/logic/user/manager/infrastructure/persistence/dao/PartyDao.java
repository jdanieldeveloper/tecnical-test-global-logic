package com.global.logic.user.manager.infrastructure.persistence.dao;

import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;

import java.util.List;

/**
 *
 *
 * @author daniel.carvajal
 */
public interface PartyDao extends Dao {

    Long nexValueForIdentifier();

    boolean saveParty(PartyDto partyDto);

    boolean saveUserLogin(PartyDto partyDto);

    boolean saveUserRole(UserRoleDto userRoleDto);

    PartyDto findPartyByUserLoginId(String userLoginId);

    List<UserRoleDto> findRoleByUserLoginId(String userLoginId);

}
