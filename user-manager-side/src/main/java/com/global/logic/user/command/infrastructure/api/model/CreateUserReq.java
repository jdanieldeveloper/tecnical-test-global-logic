package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.global.logic.user.command.domain.user.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserReq implements Serializable {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "phones")
    private Set<Phone> phones;

}
