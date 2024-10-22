package com.global.logic.user.command.infrastructure.enums;

import java.util.HashMap;

public enum RoleEnum {

	ADMIN("ADMIN", "Role Administrator with all grant"),
	VISITOR_READ("VISITOR_READ", "Role Visitor only read content"),
	VISITOR_CREATE("VISITOR_CREATE", "Role Visitor only create content"),
	VISITOR_UPDATE("VISITOR_UPDATE", "Role Visitor only update content"),
	VISITOR_DELETE("VISITOR_DELETE", "Role Visitor only delete content");

    private final String roleId;
    private final String description;

	private RoleEnum(String roleId, String description) {
		this.roleId = roleId;
		this.description = description;
	}


    public String getRoleId() {
		return roleId;
	}


	public String getDescription() {
		return description;
	}


	public static HashMap<String, String> getHashMapValues() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (RoleEnum e : RoleEnum.values()) {
            hashMap.put(e.getRoleId(), e.getDescription());
        }
        return hashMap;
    }

}
