package com.global.logic.user.manager.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StatusModel implements Serializable {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "statusDate")
    private Date statusDate;

    @JsonProperty(value = "details")
    private String details;

}
