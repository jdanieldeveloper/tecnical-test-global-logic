package com.global.logic.user.command.domain.user;

import lombok.Value;

@Value
public class Role implements Comparable<String> {

    private final String roleTypeId;
    private final String roleDescription;


    @Override
    public int compareTo(String o) {
        //TODO to be define
        return 0;
    }
}
    
