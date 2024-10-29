package com.global.logic.user.command.infrastructure.bus;


import com.global.logic.user.command.application.aggregate.UserAggregateImpl;
import com.global.logic.user.command.application.cmd.CreateUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandBus implements Bus {

    @Autowired
    private UserAggregateImpl userAggregate;


    public CreateUserCmd handle(CreateUserCmd command){
        return userAggregate.handle(command);
    }

}
