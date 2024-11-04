package com.global.logic.user.command.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.global.logic.user.command.domain.user.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserReq implements Serializable {

    @JsonProperty(value = "name")
    private String name;


    @JsonProperty(value = "email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{email.invalid.format}")
    private String email;

    @Pattern(regexp = "^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=(?:[^0-9]*[0-9]){2}[^0-9]*$)[A-Za-z0-9]{8,12}$",
            message = "{password.invalid.format}")
    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "phones")
    private Set<Phone> phones;

}
