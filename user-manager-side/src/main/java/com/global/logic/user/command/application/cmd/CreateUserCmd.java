package com.global.logic.user.command.application.cmd;

import com.global.logic.user.command.domain.user.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCmd {
	// input
	public String name;
	public String email;
	public String password;
	public Set<Phone> phones;

	// output
	public String uuid;
	private LocalDateTime createdDate;
	private LocalDateTime lastLoginDate;
	private boolean isActive;


	//errors
	@Singular
	public List<Throwable> errors;

	public boolean hasErrors(){
		return !errors.isEmpty();
	}
}
