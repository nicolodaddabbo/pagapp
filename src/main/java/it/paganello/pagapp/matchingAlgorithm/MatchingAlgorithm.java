package it.paganello.pagapp.matchingAlgorithm;

import java.util.List;

import it.paganello.pagapp.match.Match;
import it.paganello.pagapp.round.Round;

public interface MatchingAlgorithm {
    Round computeNextRound(List<Match> playedMatches);

    boolean isRoundOver(Round round);

    Round computeFirstRound();
}
