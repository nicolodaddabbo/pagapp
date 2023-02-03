package it.paganello.pagapp.matchingAlgorithm;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchingAlgorithmName {
	KNOCKOUT("Knockout"),
	ROUNDROBIN("Round-Robin"),
	SWISSDRAW("Swissdraw");
	
	private final String name;
	
	private MatchingAlgorithmName(final String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return this.name;
	}
}
