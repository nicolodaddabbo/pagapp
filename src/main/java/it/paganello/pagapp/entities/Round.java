package it.paganello.pagapp.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithmName;

@Entity
@Table(name = "ROU_ROUND")
public class Round extends EntityBaseData{
	
	@Column(name = "NUMBER")
	private int roundNumber;
	@Column(name = "FINISHED")
	private boolean finished;
	@Transient
	private int fields;
	
	@ManyToOne
	@JoinColumn(name = "TRN_ID")
	private Tournament tournament;
	
	@OneToMany(mappedBy = "round")
	private List<Match> matches;
	
	@Transient
	private List<Team> standings;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "matchingAlgorithm")
	private MatchingAlgorithmName matchingAlgorithmName;
	
	public Round() {
	}
	
	public Round(final int fields, final MatchingAlgorithmName matchingAlgorithmName) {
		this.fields = fields;
		this.matchingAlgorithmName = matchingAlgorithmName;
	}
	
	public Round(final MatchingAlgorithmName matchingAlgorithmName) {
		this.matchingAlgorithmName = matchingAlgorithmName;
	}
	
	
	public int getRoundNumber() {
		return roundNumber;
	}
	
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public int getFields() {
		return fields;
	}
	
	public void setFields(int fields) {
		this.fields = fields;
	}
	
	public Tournament getTournament() {
		return tournament;
	}
	
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	public List<Match> getMatches() {
		return matches;
	}
	
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
	public List<Team> getStandings() {
		return standings;
	}
	
	public void setStandings(List<Team> standings) {
		this.standings = standings;
	}
	
	public MatchingAlgorithmName getMatchingAlgorithmName() {
		return matchingAlgorithmName;
	}
	
	public void setMatchingAlgorithmName(MatchingAlgorithmName matchingAlgorithmName) { // forse devo passargli un id?
		this.matchingAlgorithmName = matchingAlgorithmName;
	}
	
}
