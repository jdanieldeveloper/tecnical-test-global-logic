package com.global.logic.user.query.infraestructure.api.model;

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
public class LoginModelReq implements Serializable {

    @JsonProperty(value = "userLogin")
    public String userLogin;

    @JsonProperty(value = "currentPassword")
    public String currentPassword;

}
