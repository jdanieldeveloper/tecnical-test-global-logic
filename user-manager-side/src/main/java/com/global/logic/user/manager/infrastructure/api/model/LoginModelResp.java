package com.global.logic.user.manager.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginModelResp {

    @JsonProperty(value = "user")
    private String user;

    @JsonProperty(value = "token")
    private String token;

    @JsonProperty(value = "status")
    private StatusModel status;

}

