package it.paganello.pagapp.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;
import it.paganello.pagapp.tournament.Tournament;

@Service
public class RoundService {
    @Autowired
    RoundRepository repository;

    public void checkRoundFinish(final Round round) {
        Round currentRound = repository.findById(round.getId()).orElseThrow();
        MatchingAlgorithm matchingAlgorithm = currentRound.getMatchingAlgorithm();
        if (matchingAlgorithm.isRoundOver(currentRound)) {
            repository.save(matchingAlgorithm.computeNextRound(currentRound.getMatches()));
        }
    }

    public Round firstRound(final Tournament tournament, final Round newRound) {
        Round firstRound = new Round();
        firstRound.setFields(newRound.getFields());
        firstRound.setMatchingAlgorithm(newRound.getMatchingAlgorithm());
        firstRound.setTournament(tournament);
        firstRound.setRoundNumber(1);
        firstRound.setMatches(firstRound.getMatchingAlgorithm().computeFirstRound().getMatches());
        return firstRound;
    }
}
