package com.global.logic.user.manager.infrastructure.converter;

import com.global.logic.user.manager.domain.aggregate.user.User;
import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class FromUserConverter implements Converter<User, PartyDto> {

    public PartyDto convert(User user) {
        return PartyDto.builder()
                .partyId(user.getUserId().getValue())
				.partyUuid(user.getUserUuid().getValue())
				.partyName(user.getName().orElse(""))
                .userLoginId(user.getEmail().getValue())
                .currentPassword(user.getPassword().getValue())
                .userRolesDtos(getUserRolesDtos(user))
				.phonesDtos(getPhonesDtos(user))
                .build();
    }

    public List<UserRoleDto> getUserRolesDtos(User user) {
        return user.getRoles()
                .stream()
                .flatMap(Collection::stream)
                .map(role -> UserRoleDto.builder()
                        .partyId((user.getUserId()).getValue())
                        .roleTypeId(role.getRoleTypeId())
                        .description(role.getRoleDescription())
                        .build())
                .collect(Collectors.toList());
    }

	public List<UserPhoneDto> getPhonesDtos(User user) {
		return user.getPhones()
				.stream()
				.flatMap(Collection::stream)
				.map(phone -> UserPhoneDto.builder()
						.partyId(user.getUserId().getValue())
						.countryCode(phone.getCountryCode())
						.cityCode(phone.getCityCode())
						.contactNumber(phone.getNumber())
						.build())
				.collect(Collectors.toList());
	}
}
