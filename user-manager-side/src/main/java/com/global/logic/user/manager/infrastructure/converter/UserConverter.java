package com.global.logic.user.manager.infrastructure.converter;

import com.global.logic.user.manager.domain.aggregate.user.*;
import com.global.logic.user.manager.infrastructure.api.model.PhoneModel;
import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
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
                    new UserUuid(partyDto.getUserLoginId()),
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
                .map(PartyDto::getPhonesDtos)
                .stream()
                .flatMap(Collection::stream)
                .map(phone -> new Phone(phone.getCountryCode(), phone.getCityCode(), phone.getContactNumber()))
                .collect(Collectors.toSet());
    }


    public static Function<User, PartyDto> userToPartyDto = user ->
            PartyDto.builder()
                    .partyId(user.getUserId().getValue())
                    .partyUuid(user.getUserUuid().getValue())
                    .partyName(user.getName().orElse(""))
                    .userLoginId(user.getEmail().getValue())
                    .currentPassword(user.getPassword().getValue())
                    .userRolesDtos(getUserRolesDtos(user))
                    .phonesDtos(getPhonesDtos(user))
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

    public static Function<PhoneModel, Phone> phoneModelToPhone = model ->
            new Phone(model.getCountryCode(), model.getCityCode(), model.getNumber());

    public static Function<List<PhoneModel>, List<Phone>> phoneModelsToPhones = models ->
            Optional.ofNullable(models)
                    .stream()
                    .flatMap(Collection::stream)
                    .map(phoneModelToPhone)
                    .collect(Collectors.toList());
}