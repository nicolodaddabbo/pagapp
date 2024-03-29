package it.paganello.pagapp.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchingAlgorithmName {
	KNOCKOUT("Knockout"),
	ROUNDROBIN("Round-Robin"),
	SWISSDRAW("Swissdraw");
	
	private final String name;
	
	MatchingAlgorithmName(final String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return this.name;
	}
}
