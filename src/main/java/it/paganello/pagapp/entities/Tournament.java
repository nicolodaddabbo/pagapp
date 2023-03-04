package it.paganello.pagapp.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;

/**
 * This class represents a Tournament entity, which is mapped to the "TRN_TOURNAMENT" table in the database.
 * It contains information about the tournament such as its name, creation and change timestamps, current round number,
 * type, teams participating, rounds played, and default matching algorithms used to determine matches.
 */
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
	@Column(name = "TYPE")
	private String type;
	@OneToMany(mappedBy = "tournament")
	private List<Team> teams;
	@OneToMany(mappedBy = "tournament")
	private List<Round> rounds;
	@Transient
	private List<MatchingAlgorithm> defaultMatchingAlgorithms;

	/**
	 * Default constructor for the Tournament entity class.
	 */
	public Tournament() {
	
	}

	/**
	 * Constructor for the Tournament entity class, which sets the tournament name and initializes
	 * the current round number to 1.
	 *
	 * @param name the name of the tournament
	 */
	public Tournament(final String name) {
		this.name = name;
		this.currentRoundNumber = 1;
	}

	/**
	 * Constructor for the Tournament entity class, which sets the tournament name and the teams participating.
	 *
	 * @param name the name of the tournament
	 * @param teams the list of teams participating in the tournament
	 */
	public Tournament(final String name, final List<Team> teams) {
		this.name = name;
		this.teams = new ArrayList<>(teams);
	}

	/**
	 * Returns the ID of the tournament.
	 *
	 * @return the ID of the tournament
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the ID of the tournament.
	 *
	 * @param id the ID to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Returns the creation timestamp of the tournament.
	 *
	 * @return the creation timestamp of the tournament
	 */
	public LocalDateTime getCreated() {
		return this.created;
	}

	/**
	 * Sets the creation timestamp of the tournament.
	 *
	 * @param created the creation timestamp to set
	 */
	public void setCreated(final LocalDateTime created) {
		this.created = created;
	}

	/**
	 * Returns the change timestamp of the tournament.
	 *
	 * @return the change timestamp of the tournament
	 */
	public LocalDateTime getChanged() {
		return this.changed;
	}

	/**
	 * Sets the change timestamp of the tournament.
	 *
	 * @param changed the change timestamp to set
	 */
	public void setChanged(final LocalDateTime changed) {
		this.changed = changed;
	}

	/**
	 * Returns the name of the tournament.
	 *
	 * @return the name of the tournament
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the tournament.
	 *
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the current round number of the tournament.
	 * @return the current round number
	 */
	public int getCurrentRoundNumber() {
		return this.currentRoundNumber;
	}

	/**
	 * Sets the current round number of the tournament.
	 * @param currentRoundNumber the new current round number
	 */
	public void setCurrentRoundNumber(final int currentRoundNumber) {
		this.currentRoundNumber = currentRoundNumber;
	}

	/**
	 * Returns a list of teams in the tournament.
	 * @return a list of teams
	 */
	public List<Team> getTeams() {
		return new ArrayList<>(this.teams);
	}

	/**
	 * Sets the list of teams in the tournament.
	 * @param teams the new list of teams
	 */
	public void setTeams(final List<Team> teams) {
		this.teams = teams;
	}

	/**
	 * Returns a list of rounds in the tournament.
	 * @return a list of rounds
	 */
	public List<Round> getRounds() {
		return new ArrayList<>(this.rounds);
	}

	/**
	 * Sets the list of rounds in the tournament.
	 * @param rounds the new list of rounds
	 */
	public void setRounds(final List<Round> rounds) {
		this.rounds = rounds;
	}

	/**
	 * Returns a list of default matching algorithms for the tournament.
	 * @return a list of matching algorithms
	 */
	public List<MatchingAlgorithm> getDefaultMatchingAlgorithms() {
		return new ArrayList<>(this.defaultMatchingAlgorithms);
	}

	/**
	 * Sets the list of default matching algorithms for the tournament.
	 * @param defaultMatchingAlgorithms the new list of matching algorithms
	 */
	public void setDefaultMatchingAlgorithms(final List<MatchingAlgorithm> defaultMatchingAlgorithms) {
		this.defaultMatchingAlgorithms = defaultMatchingAlgorithms;
	}

	/**
	 * Returns the type of the tournament.
	 * @return the type of the tournament
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type of the tournament.
	 * @param type the new type of the tournament
	 */
	public void setType(final String type) {
		this.type = type;
	}
}
