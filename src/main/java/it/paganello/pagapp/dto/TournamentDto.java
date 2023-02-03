package it.paganello.pagapp.dto;

import it.paganello.pagapp.enums.MatchingAlgorithmName;

import java.util.List;

public class TournamentDto {
	private Long id;
	private String name;
	private int currentRoundNumber;
	private List<TeamDto> teams;
	private List<RoundDto> rounds;
	private MatchingAlgorithmName tournamentType;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCurrentRoundNumber() {
		return currentRoundNumber;
	}
	
	public void setCurrentRoundNumber(int currentRoundNumber) {
		this.currentRoundNumber = currentRoundNumber;
	}
	
	public List<TeamDto> getTeams() {
		return teams;
	}
	
	public void setTeams(List<TeamDto> teams) {
		this.teams = teams;
	}
	
	public List<RoundDto> getRounds() {
		return rounds;
	}
	
	public void setRounds(List<RoundDto> rounds) {
		this.rounds = rounds;
	}
	
	public MatchingAlgorithmName getTournamentType() {
		return tournamentType;
	}
	
	public void setTournamentType(MatchingAlgorithmName tournamentType) {
		this.tournamentType = tournamentType;
	}
}
