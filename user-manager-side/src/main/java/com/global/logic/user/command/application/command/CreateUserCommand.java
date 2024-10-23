package com.global.logic.user.command.application.command;

import com.global.logic.user.command.domain.user.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


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
	public Set<Phone> phones;

	// output
	public String userUuid;
	public String userToken;

	//errors
	public List<Throwable> errors;

	public boolean hasErrors(){
		return errors.isEmpty();
	}
}
