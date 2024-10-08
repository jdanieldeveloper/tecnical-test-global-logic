package com.global.logic.user.manager.domain.aggregate.user;

import lombok.Value;

public class Phone implements Comparable<Long>{

    private final long number;
    private final int cityCode;
    private final String countryCode;
    
    public Phone(long number, int cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    @Override
    public int compareTo(Long o) {
        return 0;
    }
}
    
