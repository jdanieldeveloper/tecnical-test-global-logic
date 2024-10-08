package com.global.logic.user.manager.domain.aggregate.user;

public class UserIdentifier extends Identifier {

    private long value;

    public UserIdentifier(long value) {
        assertIfValidIdentifier(value);
        //
        this.value = value;
    }

    @Override
    protected void assertIfValidIdentifier(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The identifier must be a correlative number!!!");
        }
    }

    public long getIdentifier() {
        return value;
    }

}
