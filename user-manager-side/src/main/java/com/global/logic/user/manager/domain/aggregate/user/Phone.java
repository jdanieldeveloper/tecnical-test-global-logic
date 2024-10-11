package com.global.logic.user.manager.domain.aggregate.user;

import lombok.Value;

@Value
public class Phone implements Comparable<Long>{

    private final long number;
    private final int cityCode;
    private final String countryCode;

    @Override
    public int compareTo(Long o) {
        return 0;
    }
}
    
