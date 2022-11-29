package it.paganello.pagapp.matchingAlgorithm;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.paganello.pagapp.match.Match;
import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Component
public class Knockout implements MatchingAlgorithm {

    @Override
    public Round computeFirstRound(List<Team> teams) {
        Round round = new Round();
        List<Match> matches = new LinkedList<>();
        for (int i = 0; i < teams.size(); i += 2) {
            Match newMatch = new Match();
            newMatch.setHomeTeam(teams.get(i));
            newMatch.setAwayTeam(teams.get(i + 1));
            matches.add(newMatch);
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
