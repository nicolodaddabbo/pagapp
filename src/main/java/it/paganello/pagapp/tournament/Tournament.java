package it.paganello.pagapp.tournament;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;
import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Entity
public class Tournament {
    private @Id @GeneratedValue Long id;
    private String name;
    private int currentRoundNumber;
    private List<Team> teams;
    private List<Round> rounds;
    private List<MatchingAlgorithm> matchingAlgorithms;
}
