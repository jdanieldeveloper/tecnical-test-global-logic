package com.global.logic.user.manager.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyDto {
	// party fields
	private Long partyId;
	private String partyType;
	private String description;
	private String statusId;

	private Date createdDate;
	private String createByUserLogin;

	private Date lastModifiedDate;
	private String lastModifiedByUserLogin;

	// user login fields
	private String userLoginId;
	private String currentPassword;

	private List<UserPhoneDto> phonesDtos;

	private List<UserRoleDto> userRolesDtos;
}
