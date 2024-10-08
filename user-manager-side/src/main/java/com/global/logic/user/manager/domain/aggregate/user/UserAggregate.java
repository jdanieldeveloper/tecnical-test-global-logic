package com.global.logic.user.manager.domain.aggregate.user;

import com.global.logic.user.manager.application.command.CreateUserCommand;
import com.global.logic.user.manager.application.event.CreatedUserEvent;
import com.global.logic.user.manager.domain.aggregate.Aggregate;
import java.util.Optional;
import java.util.Set;

public abstract class UserAggregate implements Aggregate {

    public void handle(CreateUserCommand command) {
    }

    public User makeUser(Identifier userId, Optional<String> name, Email email, Password password, Optional<Set<Phone>> phones) {
        return new User(userId, name, email, password, phones);
    }

    public void onEvent(CreatedUserEvent event){
    }
}