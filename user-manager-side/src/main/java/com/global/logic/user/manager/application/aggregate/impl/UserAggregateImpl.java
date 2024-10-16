package com.global.logic.user.manager.application.repository.impl;

import com.global.logic.user.manager.application.command.CreateUserCommand;
import com.global.logic.user.manager.domain.aggregate.user.*;
import com.global.logic.user.manager.infrastructure.enums.RoleEnum;
import com.global.logic.user.manager.infrastructure.enums.UserTypeEnum;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor // DI by constructor
public class UserAggregateImpl extends UserAggregate {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    //private EventHandler eventHandler;


    @Override
    public Either<List<Throwable>, User> handle(CreateUserCommand command) {
        // get userId
        Either<Throwable, UserId> userId = userRepository.getUserId();
        if(userId.isLeft()) // if exist infra error
            return Either.left(List.of(userId.getLeft()));

        // get userUuid
        Either<Throwable, UserUuid> userUuid = userRepository.getUserUuid();
        if(userId.isLeft())  // if exist infra error
            return Either.left(List.of(userId.getLeft()));

            // user type
            Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
            // roles
            Role read = makeRole(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
            Role create = makeRole(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
            // phones
            Set<Phone> phones = Optional.ofNullable(command)
            // user
            User user =
                    makeUser(userId,
                               userUuid,

                               command.getUserLogin(), // user login
                               command.getCurrentPassword(), // password
                               Optional.of(Lists.newArrayList(read,create))); // roles
            // add the user
            Optional<User> addUser = userRepository.addUser(user);

            //
            if(addUser.isPresent()){
                onEvent(new CreatedUserEvent(addUser.get().getUserId().toString(), addUser.get().getUserLogin()));

                //TODO return success message
            }else{
                //TODO returns fail message
            }
        }else{
            //TODO returns fail message
        }

    // validate user with hibernate validators
    Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(violations.isEmpty()){
        return Either.right(user);
    }else{
        return Either.left(violations);
    }
    }



    @Override
    public void onEvent(CreatedUserEvent event){
        eventHandler.handler(event);
    }
}
