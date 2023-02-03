package it.paganello.pagapp.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleName {
	ADMIN("ADMIN"),
	USER("USER");
	
	private final String name;
	
	RoleName(final String name){
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return this.name;
	}
}
