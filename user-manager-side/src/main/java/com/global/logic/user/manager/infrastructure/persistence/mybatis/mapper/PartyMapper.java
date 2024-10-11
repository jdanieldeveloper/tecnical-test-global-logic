package com.global.logic.user.manager.infrastructure.persistence.mybatis.mapper;

import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PartyMapper {

	Long nexValueForIdentifier();	
	
	int saveParty(PartyDto partyDto);
	
	int saveUserLogin(PartyDto partyDto);

	int saveUserRole(UserRoleDto userRoleDto);

	PartyDto findPartyByUserLoginId(@Param("userLoginId") String userLoginId);

	List<UserRoleDto> findRoleByUserLoginId(@Param("userLoginId") String userLoginId);
	
}
