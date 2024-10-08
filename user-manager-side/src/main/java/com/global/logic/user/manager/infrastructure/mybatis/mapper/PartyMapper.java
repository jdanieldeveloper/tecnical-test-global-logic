package com.global.logic.user.manager.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import user.manager.side.infrastructure.dto.PartyDto;
import user.manager.side.infrastructure.dto.RoleDto;

import java.util.List;

@Mapper
public interface PartyMapper {

	Long nexValueForIdentifier();	
	
	int saveParty(PartyDto partyDto);
	
	int saveUserLogin(PartyDto partyDto);

	int saveRole(RoleDto roleDto);

	PartyDto findPartyByUserLoginId(@Param("userLoginId") String userLoginId);

	List<RoleDto> findRoleByUserLoginId(@Param("userLoginId") String userLoginId);
	
}
