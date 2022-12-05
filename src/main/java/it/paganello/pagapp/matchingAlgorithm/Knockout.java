package it.paganello.pagapp.matchingAlgorithm;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.paganello.pagapp.match.Match;
import it.paganello.pagapp.match.MatchService;
import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Component
public class Knockout implements MatchingAlgorithm {
    @Autowired
    MatchService matchService;

    @Override
    public Round computeFirstRound(List<Team> teams) {
        Round round = new Round();
        List<Match> matches = new LinkedList<>();
        for (int i = 0; i < teams.size(); i += 2) {
            Match newMatch = new Match();
            newMatch.setHomeTeam(teams.get(i));
            newMatch.setAwayTeam(teams.get(i + 1));
            // newMatch.setRound(round); ???
            // teams.get(i).getHomeMatches().add(newMatch);
            // teams.get(i + 1).getAwayMatches().add(newMatch);
            matches.add(newMatch);
            matchService.createMatch(newMatch);
        }
        round.setMatches(matches);
        return round;
    }

    @Override
    public Round computeNextRound(List<Match> playedMatches) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isRoundOver(Round round) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public MatchingAlgorithmName getMatchingAlgorithmName() {
        return MatchingAlgorithmName.KNOCKOUT;
    }
    
}
