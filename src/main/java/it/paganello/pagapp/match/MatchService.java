package it.paganello.pagapp.match;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.round.RoundService;

@Service
public class MatchService {
    @Autowired
    MatchRepository repository;
    @Autowired
    RoundService roundService;

    public Optional<Match> modifyMatch(final Long id, final Match newMatch) {
        return repository.findById(id)
                .map(match -> {
                    match.setHomeTeam(newMatch.getHomeTeam());
                    match.setHomeTeamPoints(newMatch.getHomeTeamPoints());
                    match.setAwayTeam(newMatch.getAwayTeam());
                    match.setField(newMatch.getField());
                    match.setAwayTeamPoints(newMatch.getAwayTeamPoints());
                    match.setFinished(newMatch.isFinished());
                    Match result = repository.save(match);
                    roundService.checkRoundFinish(match.getRound());
                    return result;
                });
    }
}
