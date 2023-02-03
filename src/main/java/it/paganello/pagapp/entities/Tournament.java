package it.paganello.pagapp.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;

@Entity
@Table(name = "TRN_TOURNAMENT")
public class Tournament {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "CREATED")
	private LocalDateTime created;
	@Column(name = "CHANGED")
	private LocalDateTime changed;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CURRENT_RND")
	private int currentRoundNumber;
	
	@OneToMany(mappedBy = "tournament")
	private List<Team> teams;
	@OneToMany(mappedBy = "tournament")
	private List<Round> rounds;
	@Transient
	private List<MatchingAlgorithm> defaultMatchingAlgorithms;
	
	public Tournament(){
	
	}
	
	public Tournament(String name) {
		this.name = name;
		this.currentRoundNumber = 1;
	}
	
	public Tournament(final String name, final List<Team> teams) {
		this.name = name;
		this.teams = new ArrayList<>(teams);
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public LocalDateTime getChanged() {
		return changed;
	}
	
	public void setChanged(LocalDateTime changed) {
		this.changed = changed;
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
	
	public List<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public List<Round> getRounds() {
		return rounds;
	}
	
	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	
	public List<MatchingAlgorithm> getDefaultMatchingAlgorithms() {
		return defaultMatchingAlgorithms;
	}
	
	public void setDefaultMatchingAlgorithms(List<MatchingAlgorithm> defaultMatchingAlgorithms) {
		this.defaultMatchingAlgorithms = defaultMatchingAlgorithms;
	}
}
