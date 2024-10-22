package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserModelResp {

    @JsonProperty(value = "user")
    private UserModelReq user;

    @JsonProperty(value = "status")
    private StatusModel status;

}
