package com.global.logic.user.manager.domain.aggregate.user;

import com.global.logic.user.manager.application.command.CreateUserCommand;
import com.global.logic.user.manager.application.event.CreatedUserEvent;
import com.global.logic.user.manager.domain.aggregate.Aggregate;
import io.vavr.control.Either;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class UserAggregate implements Aggregate {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public abstract Either<List<Throwable>, User> handle(CreateUserCommand command);

    public User makeUser(UserId userId,
                                                  UserUuid userUuid,
                                                  Optional<String> name,
                                                  Email email,
                                                  Password password,
                                                  Optional<Set<Role>> roles,
                                                  Optional<Set<Phone>> phones) {

        return new User(userId, userUuid, name, email, password, roles, phones);
    }

    public Role makeRole(String roleId, String description)  {
        return new Role(roleId, description);
    }

    public Phone makePhone(String countryCode, int cityCode, long number)  {
        return new Phone(countryCode, cityCode, number);
    }

    public void onEvent(CreatedUserEvent event){
    }
}