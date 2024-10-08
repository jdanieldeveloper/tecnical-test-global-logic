package com.global.logic.user.manager.infrastructure.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PartyDto {

	private String partyId;
	private String name;
	private String statusId;

	private Date createdDate;
	private String createByUserLogin;

	private Date lastModifiedDate;
	private String lastModifiedByUserLogin;

	// user login fields
	private String userLoginId;
	private String currentPassword;

	private Set<PhoneDto> phones;

}
