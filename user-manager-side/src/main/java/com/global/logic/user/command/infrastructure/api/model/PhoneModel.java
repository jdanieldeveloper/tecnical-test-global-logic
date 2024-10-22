package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhoneModel {

    @JsonProperty(value = "countryCode")
    private final String countryCode;

    @JsonProperty(value = "cityCode")
    private final int cityCode;

    @JsonProperty(value = "number")
    private final long number;

}
