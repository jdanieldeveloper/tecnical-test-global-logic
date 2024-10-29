package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneModel implements Serializable {

    @JsonProperty(value = "countryCode")
    private String countryCode;

    @JsonProperty(value = "cityCode")
    private int cityCode;

    @JsonProperty(value = "number")
    private long number;

}
