package com.global.logic.user.query.infraestructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.global.logic.user.command.infrastructure.api.model.ErrorModelResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserModelResp implements Serializable {

    @JsonProperty(value = "user")
    private String user;

    @JsonProperty(value = "token")
    private String token;

    @JsonProperty(value = "status")
    private ErrorModelResp status;

}

