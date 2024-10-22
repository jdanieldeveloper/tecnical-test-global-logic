package com.global.logic.user.command.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPhoneDto {
	private Long partyId;
	private Long contactMechId;
	private String contactMechTypeId;
	private Long telecomNumberId;
	//
	private String countryCode;
	private int cityCode;
	private long contactNumber;
}
