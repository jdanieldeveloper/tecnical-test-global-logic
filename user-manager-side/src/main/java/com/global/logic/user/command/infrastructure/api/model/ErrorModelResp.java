package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModelResp implements Serializable {

    @Singular
    @JsonProperty(value = "errors")
    public List<CustomError> customErrors;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomError {

        @JsonProperty(value = "timestamp")
        private LocalDateTime timeStamp;

        @JsonProperty(value = "codigo")
        private int code;

        @JsonProperty(value = "detail")
        private String detail;
    }

}
