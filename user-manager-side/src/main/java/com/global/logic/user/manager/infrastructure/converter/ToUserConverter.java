package com.global.logic.user.manager.infrastructure.converter;

import com.global.logic.user.manager.domain.aggregate.user.*;
import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ToUserConverter implements Converter<PartyDto, User> {

	@Override
	public User convert(PartyDto partyDto) {
		return new User(new UserId(partyDto.getPartyId()),
						new UserUuid(partyDto.getUserLoginId()),
						Optional.ofNullable(partyDto.getPartyName()),
						new Email(partyDto.getUserLoginId()),
						new Password(partyDto.getCurrentPassword()),
						Optional.ofNullable(geRoles(partyDto)),
						Optional.ofNullable(getPhones(partyDto)));
	}

	public Set<Role> geRoles(PartyDto partyDto) {
		return Optional.ofNullable(partyDto)
				.map(PartyDto::getUserRolesDtos)
				.stream()
				.flatMap(Collection::stream)
				.map(role -> new Role(role.getRoleTypeId(), role.getDescription()))
				.collect(Collectors.toSet());
	}

	public Set<Phone> getPhones(PartyDto partyDto) {
		return Optional.ofNullable(partyDto)
				.map(PartyDto::getPhonesDtos)
				.stream()
				.flatMap(Collection::stream)
				.map(phone -> new Phone(phone.getCountryCode(), phone.getCityCode(), phone.getContactNumber()))
				.collect(Collectors.toSet());
	}
}
