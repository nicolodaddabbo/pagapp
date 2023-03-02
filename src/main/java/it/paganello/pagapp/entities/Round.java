package it.paganello.pagapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import it.paganello.pagapp.enums.MatchingAlgorithmName;

@Entity
@Table(name = "ROU_ROUND")
public class Round {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "CREATED")
	private LocalDateTime created;
	@Column(name = "CHANGED")
	private LocalDateTime changed;
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
	
}
