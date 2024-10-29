package com.global.logic.user.command.infrastructure.dto;

import com.global.logic.user.command.infrastructure.enums.UserStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyDto {
	// party fields
	private Long partyId;
	private String partyUuid;
	private String partyType;
	private String partyName;
	private String description;
	private String statusId;

	private LocalDateTime createdDate;
	private String createByUserLogin;

	private LocalDateTime lastLoginDate;
	private LocalDateTime lastModifiedDate;
	private String lastModifiedByUserLogin;

	// user login fields
	private String userLoginId;
	private String currentPassword;

	@Singular
	private List<UserPhoneDto> phonesDtos;

	@Singular
	private List<UserRoleDto> userRolesDtos;

	public boolean isActive() {
		return UserStatusEnum.ENABLED.getStatusId().equals(this.statusId);
	}
}
