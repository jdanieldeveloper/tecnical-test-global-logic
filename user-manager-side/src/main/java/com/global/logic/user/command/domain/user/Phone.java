package com.global.logic.user.command.domain.user;

import lombok.Value;

@Value
public class Phone implements Comparable<Long>{

    private final String countryCode;
    private final int cityCode;
    private final long number;


    @Override
    public int compareTo(Long o){
        //TODO to be define
        return 0;
    }
}
    
