package com.global.logic.user.query.infraestructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.global.logic.user.command.infrastructure.api.model.PhoneModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginModelResp implements Serializable {

    @JsonProperty(value = "id")
    private String uuid;

    @JsonProperty(value = "created")
    private LocalDateTime createdDate;

    @JsonProperty(value = "lastLogin")
    private LocalDateTime lastLoginDate;

    @JsonProperty(value = "token")
    private String token;

    @JsonProperty(value = "isActive")
    private boolean isActive;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    @Singular
    @JsonProperty(value = "phones")
    private List<PhoneModel> phones;

}

