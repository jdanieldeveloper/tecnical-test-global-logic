package com.global.logic.user.command.infrastructure.enums;

import java.util.HashMap;

public enum UserStatusEnum {

	ENABLED("ENABLED", "Enabled"),
	DISABLED("DISABLED", "Disabled");


    private final String statusId;
    private final String description;

	private UserStatusEnum(String userStatusId, String description) {
		this.statusId = userStatusId;
		this.description = description;
	}


    public String getStatusId() {
		return statusId;
	}


	public String getDescription() {
		return description;
	}


	public static HashMap<String, String> getHashMapValues() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (UserStatusEnum e : UserStatusEnum.values()) {
            hashMap.put(e.getStatusId(), e.getDescription());
        }
        return hashMap;
    }

}
