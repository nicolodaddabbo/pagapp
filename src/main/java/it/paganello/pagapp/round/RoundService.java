package it.paganello.pagapp.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;
import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithmFactory;
import it.paganello.pagapp.tournament.Tournament;

@Service
public class RoundService {
    @Autowired
    RoundRepository repository;
    @Autowired
    MatchingAlgorithmFactory matchingAlgorithmFactory;

    public void checkRoundFinish(final Round round) {
        Round currentRound = repository.findById(round.getId()).orElseThrow();
        MatchingAlgorithm matchingAlgorithm = matchingAlgorithmFactory.findMatchingAlgorithm(round.getMatchingAlgorithmName());
        if (matchingAlgorithm.isRoundOver(currentRound)) {
            repository.save(matchingAlgorithm.computeNextRound(currentRound.getMatches()));
        }
    }

    public Round firstRound(final Tournament tournament, final Round newRound) {
        Round firstRound = new Round();
        firstRound.setFields(newRound.getFields());
        firstRound.setMatchingAlgorithmName(newRound.getMatchingAlgorithmName());
        firstRound.setTournament(tournament);
        firstRound.setRoundNumber(1);
        firstRound.setMatches(matchingAlgorithmFactory.findMatchingAlgorithm(newRound.getMatchingAlgorithmName()).computeFirstRound(tournament.getTeams()).getMatches());
        return firstRound;
    }
}
