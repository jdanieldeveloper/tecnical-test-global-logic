package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginModelReq {

    @JsonProperty(value = "userLogin")
    public String userLogin;

    @JsonProperty(value = "currentPassword")
    public String currentPassword;

}
