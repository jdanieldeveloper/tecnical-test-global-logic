package com.global.logic.user.query.infraestructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginModelReq implements Serializable {

    @JsonProperty(value = "email")
    public String email;

    @JsonProperty(value = "password")
    @Pattern(regexp = "^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=(?:[^0-9]*[0-9]){2}[^0-9]*$)[A-Za-z0-9]{8,12}$",
            message = "{password.invalid.format}")
    public String password;

}
