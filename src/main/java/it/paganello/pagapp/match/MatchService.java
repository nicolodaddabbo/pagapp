package it.paganello.pagapp.match;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.team.Team;

@Service
public class MatchService {
    @Autowired
    MatchRepository repository;

    public Optional<String> updateMatchResult(final Long id, final int homeTeamPoints, final int awayTeamPoints) {
        Optional<Match> match = repository.findById(id);
        if (match.isEmpty()) {
            return Optional.of("Match not found");
        }
        if (match.get().isFinished()) {
            updateFinishedMatch(match.get());
        }
        match.get().setHomeTeamPoints(homeTeamPoints);
        match.get().setAwayTeamPoints(awayTeamPoints);
        match.get().setFinished(true);
        repository.save(match.get());
        return Optional.empty();
    }

    private void updateFinishedMatch(Match match) {
        match.setUpdated(false);
        if (match.getHomeTeamPoints() > match.getAwayTeamPoints()) {
            cleanTeamPoints(match.getHomeTeam(), match.getAwayTeam());
        } else {
            cleanTeamPoints(match.getAwayTeam(), match.getHomeTeam());
        }
    }

    private void cleanTeamPoints(Team winner, Team loser) {
        winner.setWins(winner.getWins() - 1);
        loser.setLosses(loser.getLosses() - 1);
        // points?
    }

    public Match createMatch(final Match match) {
        return repository.save(match);
    }

    public Optional<Match> getMatchById(final Long id) {
        return repository.findById(id);
    }
}
