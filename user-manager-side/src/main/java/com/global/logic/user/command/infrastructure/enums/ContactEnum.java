package com.global.logic.user.command.infrastructure.enums;

import java.util.HashMap;

public enum ContactEnum {

	TELECOM_NUMBER("TELECOM NUMBER", "Telecom number associated"),
	POSTAL_ADDRESS("POSTAL_ADDRESS", "Postal address associated");

    private final String contactId;
    private final String description;

	private ContactEnum(String contactId, String description) {
		this.contactId = contactId;
		this.description = description;
	}


    public String getContactId() {
		return contactId;
	}


	public String getDescription() {
		return description;
	}


	public static HashMap<String, String> getHashMapValues() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (ContactEnum e : ContactEnum.values()) {
            hashMap.put(e.getContactId(), e.getDescription());
        }
        return hashMap;
    }

}
