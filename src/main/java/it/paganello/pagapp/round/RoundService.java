package it.paganello.pagapp.round;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;
import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithmFactory;
import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithmName;
import it.paganello.pagapp.tournament.Tournament;

@Service
public class RoundService {
    @Autowired
    RoundRepository repository;
    @Autowired
    MatchingAlgorithmFactory matchingAlgorithmFactory;

    public Round addRound(final Round round) {
        return repository.save(round);
    }

    public List<Round> computeRound(final Tournament tournament, final String matchingAlgorithmString) {
        List<Round> rounds = new LinkedList<>();
        MatchingAlgorithm matchingAlgorithm = matchingAlgorithmFactory.findMatchingAlgorithm(MatchingAlgorithmName.valueOf(matchingAlgorithmString));
        if (!tournament.getRounds().isEmpty() && !isRoundOver(tournament, tournament.getRounds())) {
            return rounds;
        }

        rounds = matchingAlgorithm.compute(tournament.getTeams());
        rounds.forEach(r -> {
            r.setTournament(tournament);
            r.setMatchingAlgorithmName(matchingAlgorithm.getMatchingAlgorithmName());
            r.getMatches().forEach(m -> m.setRound(r));
        });
        tournament.setCurrentRoundNumber(rounds.get(0).getRoundNumber());
        repository.saveAll(rounds);
        return rounds;
    }

    public boolean isRoundOver(final Tournament tournament, final List<Round> rounds) {
        for (Round round : rounds) {
            MatchingAlgorithm matchingAlgorithm = matchingAlgorithmFactory.findMatchingAlgorithm(round.getMatchingAlgorithmName());
            if (matchingAlgorithm.isRoundOver(round)) {
                if (!round.isFinished()) {
                    round.setFinished(true);
                    tournament.setCurrentRoundNumber(tournament.getCurrentRoundNumber() + 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public void checkRoundFinish(final Round round) {
        Round currentRound = repository.findById(round.getId()).orElseThrow();
        MatchingAlgorithm matchingAlgorithm = matchingAlgorithmFactory.findMatchingAlgorithm(round.getMatchingAlgorithmName());
        if (matchingAlgorithm.isRoundOver(currentRound)) {
            repository.saveAll(matchingAlgorithm.compute(currentRound.getTournament().getTeams()));
        }
    }

}
