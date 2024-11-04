package com.global.logic.user.command.domain.user;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Value
public class User {

    @Valid
    @NotNull(message = "{user.identifier.notnull}")
    private final UserId userId;

    @Valid
    @NotNull(message = "{user.uuid.identifier.notnull}")
    private final UserUuid userUuid;

    @Valid
    @NotNull(message = "{user.type.notnull}")
    private final Type userType;

    private final Optional<String> name;

    @Valid
    @NotNull(message = "{user.email.notnull}")
    private final Email email;

    @Valid
    @NotNull(message = "{user.password.notnull}")
    private final Password password;

    private final Optional<Set<Role>> roles;
    private final Optional<Set<Phone>> phones;

}
