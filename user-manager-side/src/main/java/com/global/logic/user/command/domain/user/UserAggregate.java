package com.global.logic.user.command.domain.user;

import com.global.logic.user.command.application.command.CreateUserCommand;
import com.global.logic.user.command.application.event.CreatedUserEvent;
import com.global.logic.user.command.domain.Aggregate;

import java.util.Optional;
import java.util.Set;

public abstract class UserAggregate implements Aggregate {


    public abstract CreateUserCommand handle(CreateUserCommand command);

    public User makeUser(UserId userId,
                         UserUuid userUuid,
                         Type userType,
                         Optional<String> name,
                         Email email,
                         Password password,
                         Optional<Set<Role>> roles,
                         Optional<Set<Phone>> phones) {

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }

    public Role makeRole(String roleId, String description) {
        return new Role(roleId, description);
    }

    public abstract void onEvent(CreatedUserEvent event);
}