package it.paganello.pagapp.dto;

import javax.persistence.Column;

public class TeamDto {
	private Long id;
	private String name;
	private int wins;
	private int losses;
	private double swissPoints;
	private int goalDifference;
	private int seed;
	private char pool;
	private Long tournamentId;
	
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
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public double getSwissPoints() {
		return swissPoints;
	}
	
	public void setSwissPoints(double swissPoints) {
		this.swissPoints = swissPoints;
	}
	
	public int getGoalDifference() {
		return goalDifference;
	}
	
	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}
	
	public int getSeed() {
		return seed;
	}
	
	public void setSeed(int seed) {
		this.seed = seed;
	}
	
	public char getPool() {
		return pool;
	}
	
	public void setPool(char pool) {
		this.pool = pool;
	}
	
	public Long getTournamentId() {
		return tournamentId;
	}
	
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
}
