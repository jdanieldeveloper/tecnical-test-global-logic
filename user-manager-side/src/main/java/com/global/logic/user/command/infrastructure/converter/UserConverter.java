package com.global.logic.user.command.infrastructure.converter;

import com.global.logic.user.command.domain.user.*;
import com.global.logic.user.command.infrastructure.api.model.PhoneModel;
import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class UserConverter {

    public static Function<PartyDto, User> partyDtoToUser = partyDto ->
            new User(new UserId(partyDto.getPartyId()),
                    new UserUuid(partyDto.getPartyUuid()),
                    new Type(partyDto.getPartyType()),
                    Optional.ofNullable(partyDto.getPartyName()),
                    new Email(partyDto.getUserLoginId()),
                    new Password(partyDto.getCurrentPassword()),
                    Optional.ofNullable(geRoles(partyDto)),
                    Optional.ofNullable(getPhones(partyDto)));

    public static Set<Role> geRoles(PartyDto partyDto) {
        return Optional.ofNullable(partyDto)
                .map(PartyDto::getUserRolesDtos)
                .stream()
                .flatMap(Collection::stream)
                .map(role -> new Role(role.getRoleTypeId(), role.getDescription()))
                .collect(Collectors.toSet());
    }

    public static Set<Phone> getPhones(PartyDto partyDto) {
        return Optional.ofNullable(partyDto)
                .map(PartyDto::getUserPhonesDtos)
                .stream()
                .flatMap(Collection::stream)
                .map(phone -> new Phone(phone.getCountryCode(), phone.getCityCode(), phone.getContactNumber()))
                .collect(Collectors.toSet());
    }


    public static Function<User, PartyDto> userToPartyDto = user ->
            PartyDto.builder()
                    .partyId(user.getUserId().getValue())
                    .partyUuid(user.getUserUuid().getValue())
                    .partyType(user.getUserType().getTypeId())
                    .partyName(user.getName().orElse(""))
                    .userLoginId(user.getEmail().getValue())
                    .currentPassword(user.getPassword().getValue())
                    .userRolesDtos(getUserRolesDtos(user))
                    .userPhonesDtos(getPhonesDtos(user))
                    .build();

    public static List<UserRoleDto> getUserRolesDtos(User user) {
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

    public static List<UserPhoneDto> getPhonesDtos(User user) {
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