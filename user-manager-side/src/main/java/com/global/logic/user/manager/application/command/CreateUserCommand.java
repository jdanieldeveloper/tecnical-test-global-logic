package com.global.logic.user.manager.application.command;

import com.global.logic.user.manager.infrastructure.api.model.PhoneModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand {
	// input
	public String name;
	public String email;
	public String password;
	public String currentPassword;
	public List<PhoneModel> phones;

	// output
	public String userUuid;
	public LocalDateTime createdDate;
	public LocalDateTime lastLoginDate;
	public String userToken;

	//errors
	public List<Throwable> errors;

	public boolean hasErrors(){
		return errors.isEmpty();
	}
}
