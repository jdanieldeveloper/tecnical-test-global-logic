package com.global.logic.user.manager.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserModelReq {

    @JsonProperty(value = "userLogin")
    public String userLogin;

    @JsonProperty(value = "currentPassword")
    public String currentPassword;

}
