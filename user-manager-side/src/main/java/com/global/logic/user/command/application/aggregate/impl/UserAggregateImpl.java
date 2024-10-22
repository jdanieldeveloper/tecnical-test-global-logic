package com.global.logic.user.command.application.aggregate.impl;

import com.global.logic.user.command.application.command.CreateUserCommand;
import com.global.logic.user.command.application.event.CreatedUserEvent;
import com.global.logic.user.command.domain.aggregate.user.*;
import com.global.logic.user.command.infrastructure.enums.RoleEnum;
import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;
import com.global.logic.user.command.infrastructure.exception.BusinessException;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor // DI by constructor
public class UserAggregateImpl extends UserAggregate {

    private Validator userValidator;
    private UserRepository userRepository;

    @Autowired
    //private EventHandler eventHandler;

    @Override
    public CreateUserCommand handle(CreateUserCommand command) {
        // generated userId
        Either<Throwable, UserId> userIdGenerated = userRepository.getUserId();
        if (userIdGenerated.isLeft()) { // if exist infra error
            return CreateUserCommand.builder()
                    .errors(List.of(userIdGenerated.getLeft()))
                    .build();
        }
        // generated userUuid
        Either<Throwable, UserUuid> userUuidGenerated = userRepository.getUserUuid();
        if (userIdGenerated.isLeft())  // if exist infra error
            return CreateUserCommand.builder()
                    .errors(List.of(userUuidGenerated.getLeft()))
                    .build();

        // userId
        UserId userId = userIdGenerated.get();
        // userUuid
        UserUuid userUuid = userUuidGenerated.get();
        // name can be optional
        Optional<String> name = Optional.ofNullable(command.getName());
        // user type
        Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
        // email
        Email email = new Email(command.getEmail());
        // password
        Password password = new Password(command.getPassword());

        // roles can be optional but in this case assign by default
        Role read = makeRole(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = makeRole(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones = Optional.ofNullable(command.getPhones());

        // make user and set fields
        User user = makeUser(userId, userUuid, userType, name, email, password, roles, phones);

        // validate user before store
        Either<List<Throwable>, User> validatedUser = isValid(user);
        if(validatedUser.isLeft()) { // if exist domain errors
             return CreateUserCommand.builder()
                    .errors(validatedUser.getLeft())
                    .build();
        }

        // add the user to store
        Either<Throwable, User> addedUser = userRepository.addUser(user);
        if (addedUser.isLeft()) { // if exist infra error
            return CreateUserCommand.builder()
                    .errors(List.of(addedUser.getLeft()))
                    .build();
        }

        // if you want can send the business event and propagate action
        //handle(new CreateUserCommand(...)

        return CreateUserCommand.builder()
                .userUuid(user.getUserUuid().getValue())
                .build();
    }


    public Either<List<Throwable>, User> isValid(User user) {
        Set<ConstraintViolation<User>> violations = userValidator.validate(user);
        if (violations.isEmpty()) {
            return Either.right(user);
        } else {
            return Either.left(
                    violations.stream()
                            .map(ConstraintViolation::getMessage)
                            .map(BusinessException::new)
                            .collect(Collectors.toList()));
        }
    }

    @Override
    public void onEvent(CreatedUserEvent event) {
        //eventHandler.handler(event);
    }
}
