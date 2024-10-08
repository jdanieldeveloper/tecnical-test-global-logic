package com.global.logic.user.manager.infrastructure.mybatis.dao;

import user.manager.side.infrastructure.dto.PartyDto;
import user.manager.side.infrastructure.dto.RoleDto;

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

    boolean saveUserRole(RoleDto roleDto);

    PartyDto findPartyByUserLoginId(String userLoginId);

    List<RoleDto> findRoleByUserLoginId(String userLoginId);

}
