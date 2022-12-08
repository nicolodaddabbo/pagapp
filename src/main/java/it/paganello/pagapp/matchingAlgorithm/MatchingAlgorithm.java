package it.paganello.pagapp.matchingAlgorithm;

import java.util.List;

import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

public interface MatchingAlgorithm {
    List<Round> compute(List<Team> teams);

    boolean isRoundOver(Round round);

    Round computeFirstRound(List<Team> teams);

    MatchingAlgorithmName getMatchingAlgorithmName();
}
