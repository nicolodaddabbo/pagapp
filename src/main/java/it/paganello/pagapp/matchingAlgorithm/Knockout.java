package it.paganello.pagapp.matchingAlgorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.paganello.pagapp.match.Match;
import it.paganello.pagapp.match.MatchService;
import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Component
public class Knockout implements MatchingAlgorithm {
    @Autowired
    private MatchService matchService;

    @Override
    public Round computeFirstRound(List<Team> teams) {
        // Round round = new Round();
        // List<Match> matches = new LinkedList<>();
        // for (int i = 0; i < teams.size(); i += 2) {
        //     Match newMatch = new Match();
        //     newMatch.setHomeTeam(teams.get(i));
        //     newMatch.setAwayTeam(teams.get(i + 1));
        //     matches.add(newMatch);
        //     matchService.createMatch(newMatch);
        // }
        // round.setMatches(matches);
        // return round;
        return compute(teams).get(1);
    }

    @Override
    public List<Round> compute(List<Team> teams) {
        // if (teams.get(0).getHomeMatches() == 0) {
        //     // computeRoundRobin
        // } else {
                // teams.sort((arg0, arg1) -> Double.compare(arg0.getSwissPoints(), arg1.getSwissPoints())); // TODO
        //     // knockout
        // }
        Map<Character, List<Team>> pools = teams.stream().collect(Collectors.groupingBy(Team::getPool));
        List<Round> rounds = new LinkedList<>();
        for (var pool : pools.entrySet()) {
            rounds.addAll(computeRoundRobin(pool));
        }
        return rounds;
    }

    List<Round> computeRoundRobin(Entry<Character, List<Team>> pool) {
        List<Round> result = new LinkedList<>();
        List<Team> teams = pool.getValue();
        if (teams.size() % 2 != 0) {
            teams.add(new Team("Bye")); // add a dummy team, who match it gets a bye round
        }

        int nRounds = teams.size() - 1;
        int halfSize = teams.size() / 2;
        for (int i = 1; i <= nRounds ; i++) {
            Round r = new Round();
            r.setRoundNumber(i);
            List<Match> matches = new LinkedList<>();
            for (int d = 0; d < halfSize; d++) {
                Match m = new Match();
                m.setHomeTeam(teams.get(d));
                m.setAwayTeam(teams.get(teams.size() - d - 1));
                matchService.createMatch(m);
                matches.add(m);
            }
            r.setMatches(matches);
            result.add(r);
            List<Team> rotationTeams = teams.subList(1, teams.size());
            Collections.rotate(rotationTeams, 1);
            for (int j = 1; j < teams.size(); j++) {
                teams.set(j, rotationTeams.get(j - 1));
            }
        }

        return result;
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
