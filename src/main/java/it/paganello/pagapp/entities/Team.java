package it.paganello.pagapp.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TEA_TEAM")
public class Team extends EntityBaseData{
	
	@Column(name = "NAME")
	private String name;
	@Column(name = "WINS")
	private int wins;
	@Column(name = "LOSSES")
	private int losses;
	@Column(name = "POINTS")
	private double swissPoints;
	@Column(name = "GOAL_DIFFERENCE")
	private int goalDifference;
	@Column(name = "SEED")
	private int seed;
	@Column(name = "POOL")
	private char pool;
	@ManyToOne
	@JoinColumn(name = "TRN_ID")
	private Tournament tournament;
	
	@OneToMany(mappedBy = "homeTeam")
	private List<Match> homeMatches;
	
	@OneToMany(mappedBy = "awayTeam")
	private List<Match> awayMatches;
	
	
	
	public Team(){
	}
	
	public Team(final String name) {
		this.name = name;
	}
	
	public Team(final String name, final int seed) {
		this.name = name;
		this.seed = seed;
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
	
	public void setSwissPoints(double points) {
		this.swissPoints = points;
	}
	
	public int getGoalDifference() {
		return goalDifference;
	}
	
	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}
	
	public List<Match> getHomeMatches() {
		return homeMatches;
	}
	
	public void setHomeMatches(List<Match> homeMatches) {
		this.homeMatches = homeMatches;
	}
	
	public List<Match> getAwayMatches() {
		return awayMatches;
	}
	
	public void setAwayMatches(List<Match> awayMatches) {
		this.awayMatches = awayMatches;
	}
	
	public Tournament getTournament() {
		return tournament;
	}
	
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
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
	
}
