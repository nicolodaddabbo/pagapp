package it.paganello.pagapp.match;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    @Autowired
    MatchRepository repository;

    public Optional<String> updateMatchResult(final Long id, final int homeTeamPoints, final int awayTeamPoints) {
        Optional<Match> match = repository.findById(id);
        if (match.isEmpty()) {
            return Optional.of("Match not found");
        }
        match.get().setHomeTeamPoints(homeTeamPoints);
        match.get().setAwayTeamPoints(awayTeamPoints);
        match.get().setFinished(true);
        repository.save(match.get());
        return Optional.empty();
    }

    public Match createMatch(final Match match) {
        return repository.save(match);
    }

    public Optional<Match> getMatchById(final Long id) {
        return repository.findById(id);
    }
}
