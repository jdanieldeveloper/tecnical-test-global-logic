package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResp implements Serializable {

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
}
