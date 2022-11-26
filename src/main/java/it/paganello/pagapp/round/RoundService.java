package it.paganello.pagapp.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;

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
}
