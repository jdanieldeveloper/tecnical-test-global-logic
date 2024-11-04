package com.global.logic.user.command.infrastructure.persistence.mybatis.mapper;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PartyMapper {

	Long nextValueForIdentifier();

	Long nextValueForContact();
	
	int saveParty(PartyDto partyDto);
	
	int saveUserLogin(PartyDto partyDto);

	int saveUserRole(UserRoleDto userRoleDto);

	int saveUserContact(UserPhoneDto userRoleDto);

	int saveUserPhone(UserPhoneDto userRoleDto);

	PartyDto findPartyByUserLoginId(@Param("userLoginId") String userLoginId);

	List<UserRoleDto> findRoleByUserLoginId(@Param("userLoginId") String userLoginId);

	List<UserPhoneDto> findPhoneByUserLoginId(@Param("userLoginId") String userLoginId);
}
