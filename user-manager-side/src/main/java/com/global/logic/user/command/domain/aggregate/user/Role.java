package com.global.logic.user.command.domain.aggregate.user;

import lombok.Value;

@Value
public class Role implements Comparable<String> {

    private final String roleTypeId;
    private final String roleDescription;


    @Override
    public int compareTo(String o) {
        return 0;
    }
}
    
