package com.global.logic.user.manager.domain.aggregate.user;

import lombok.Value;

import java.util.Optional;
import java.util.Set;

@Value
public class User {

    private final Id userId;
    private final Optional<String> name;
    private final Email email;
    private final Password password;
    private final Optional<Set<Role>> roles;
    private final Optional<Set<Phone>> phones;

}
