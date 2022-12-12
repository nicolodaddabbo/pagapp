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
        // if (!matchingAlgorithm.isRoundOver(tournament.getRounds().get(tournament.getCurrentRoundNumber()))) {
        //     return rounds;
        // }

        rounds = matchingAlgorithm.compute(tournament.getTeams());
        rounds.forEach(r -> {
            r.setTournament(tournament);
            r.getMatches().forEach(m -> m.setRound(r));
        });
        repository.saveAll(rounds);
        return rounds;
    }

    public void checkRoundFinish(final Round round) {
        Round currentRound = repository.findById(round.getId()).orElseThrow();
        MatchingAlgorithm matchingAlgorithm = matchingAlgorithmFactory.findMatchingAlgorithm(round.getMatchingAlgorithmName());
        if (matchingAlgorithm.isRoundOver(currentRound)) {
            repository.saveAll(matchingAlgorithm.compute(currentRound.getTournament().getTeams()));
        }
    }

    public Round firstRound(final Tournament tournament, final Round newRound) {
        newRound.setTournament(tournament);
        newRound.setRoundNumber(1);
        newRound.setMatches(matchingAlgorithmFactory.findMatchingAlgorithm(newRound.getMatchingAlgorithmName()).computeFirstRound(tournament.getTeams()).getMatches());
        newRound.getMatches().forEach(m -> m.setRound(newRound));
        repository.save(newRound);
        return newRound;
    }
}
