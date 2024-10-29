package com.global.logic.user.command.infrastructure.bus;


import com.global.logic.user.command.application.aggregate.UserAggregateImpl;
import com.global.logic.user.command.application.cmd.CreateUserCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventBus implements Bus {

    @Autowired
    private UserAggregateImpl userAggregate;

    public void handle(CreateUserCmd command){
        userAggregate.handle(command);
    }


}
