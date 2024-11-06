package com.global.logic.user.command.infrastructure.converter;

import com.global.logic.user.command.domain.user.Phone;
import com.global.logic.user.command.infrastructure.api.model.ErrorModelResp;
import com.global.logic.user.command.infrastructure.api.model.PhoneModel;
import com.global.logic.user.command.infrastructure.dto.UserPhoneDto;
import com.global.logic.user.command.infrastructure.exception.CustomException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class UserModelConverter {

    public static Function<CustomException, ErrorModelResp.CustomError> customExceptionToCustomError = exception ->
            ErrorModelResp.CustomError.builder()
                    .timeStamp(LocalDateTime.now())
                    .code(exception.getCode())
                    .detail(exception.getMessage())
                    .build();

    public static Function<PhoneModel, Phone> phoneModelToPhone = model ->
            new Phone(model.getCountryCode(), model.getCityCode(), model.getNumber());

    public static Function<List<PhoneModel>, Set<Phone>> phoneModelsToPhones = models ->
            Optional.ofNullable(models)
                    .stream()
                    .flatMap(Collection::stream)
                    .map(phoneModelToPhone)
                    .collect(Collectors.toSet());

    public static Function<UserPhoneDto, PhoneModel> userPhoneDtoToPhoneModel = dto ->
             PhoneModel.builder()
                     .countryCode(dto.getCountryCode())
                     .cityCode(dto.getCityCode())
                     .number(dto.getContactNumber())
                     .build();

    public static Function<List<UserPhoneDto>, List<PhoneModel>> userPhoneDtosToPhoneModels = dtos ->
            Optional.ofNullable(dtos)
                    .stream()
                    .flatMap(Collection::stream)
                    .map(userPhoneDtoToPhoneModel)
                    .collect(Collectors.toList());
}