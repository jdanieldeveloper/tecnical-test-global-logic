package com.global.logic.user.command.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
	private Long partyId;
	private String roleTypeId;
	private String description;
	
}
