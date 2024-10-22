package com.global.logic.user.command.domain.aggregate.user;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Value
public class User {

    @NotNull(message = "The user Identifier can't be null")
    private final UserId userId;

    @NotNull(message = "The user uuid can't be null")
    private final UserUuid userUuid;

    @NotNull(message = "The user type can't be null")
    private final Type userType;

    private final Optional<String> name;

    @NotNull(message = "The user email can't be null")
    private final Email email;

    @NotNull(message = "The user password can't be null")
    private final Password password;

    private final Optional<Set<Role>> roles;
    private final Optional<Set<Phone>> phones;

}
