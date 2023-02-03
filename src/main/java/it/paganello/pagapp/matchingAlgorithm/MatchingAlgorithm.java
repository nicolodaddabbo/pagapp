package it.paganello.pagapp.matchingAlgorithm;

import java.util.List;

import it.paganello.pagapp.entities.Round;
import it.paganello.pagapp.entities.Team;

public interface MatchingAlgorithm {
	List<Round> compute(List<Team> teams);
	
	boolean isRoundOver(Round round);
	
	MatchingAlgorithmName getMatchingAlgorithmName();
}
