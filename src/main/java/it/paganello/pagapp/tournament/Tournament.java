package it.paganello.pagapp.tournament;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;
import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Entity
@Table(name = "TRN_TOURNAMENT")
public class Tournament {
    private @Id @GeneratedValue Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CURRENT_RND")
    private int currentRoundNumber;

    @OneToMany(mappedBy = "tournament")
    @JsonManagedReference
    private List<Team> teams;
    @OneToMany(mappedBy = "tournament")
    @JsonManagedReference
    private List<Round> rounds;
    @Transient
    private List<MatchingAlgorithm> defaultMatchingAlgorithms;

    public Tournament(){
        
    }

    public Tournament(String name) {
        this.name = name;
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

    public List<MatchingAlgorithm> getMatchingAlgorithms() {
        return defaultMatchingAlgorithms;
    }

    public void setMatchingAlgorithms(List<MatchingAlgorithm> matchingAlgorithms) {
        this.defaultMatchingAlgorithms = matchingAlgorithms;
    }

    
}
