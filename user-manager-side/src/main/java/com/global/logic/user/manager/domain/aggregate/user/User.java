package com.global.logic.user.manager.domain.aggregate.user;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Value
public class User {

    @NotNull(message = "The userId can't be null")
    private final UserId userId;

    @NotNull(message = "The userUuid can't be null")
    private final UserUuid userUuid;

    private final Optional<String> name;

    @NotNull(message = "The email can't be null")
    private final Email email;

    @NotNull(message = "The email can't be null")
    private final Password password;

    private final Optional<Set<Role>> roles;
    private final Optional<Set<Phone>> phones;

}
