package it.paganello.pagapp.matchingAlgorithm;

import java.util.Collections;
import java.util.Comparator;
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
    private static final int WIN_POINTS = 3;
    private static final int LOST_POINTS = 0;

    @Autowired
    private MatchService matchService;

    @Override
    public List<Round> compute(List<Team> teams) {
        List<Round> rounds = new LinkedList<>();

        if (teams.get(0).getHomeMatches().size() == 0) { // round robin
            Map<Character, List<Team>> pools = teams.stream().collect(Collectors.groupingBy(Team::getPool));
            for (var pool : pools.entrySet()) {
                rounds.addAll(computeRoundRobin(pool));
            }
        } else { // knockout
            teams.sort(this.new StandingOrder());
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
        for (int i = 1; i <= nRounds; i++) {
            Round r = new Round();
            r.setRoundNumber(i);
            List<Match> matches = new LinkedList<>();
            for (int d = 0; d < halfSize; d++) {
                Match m = new Match();
                m.setHomeTeam(teams.get(d));
                m.setAwayTeam(teams.get(teams.size() - d - 1));
                m.setUpdated(false);
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
        for (Match m : round.getMatches()) {
            if (!m.isFinished()) {
                return false;
            }
            if (!m.isUpdated()) {
                Team homeTeam = m.getHomeTeam();
                Team awayTeam = m.getAwayTeam();
    
                homeTeam.setGoalDifference(
                        homeTeam.getGoalDifference()
                                + m.getHomeTeamPoints()
                                - m.getAwayTeamPoints());
    
                awayTeam.setGoalDifference(
                        awayTeam.getGoalDifference()
                                + m.getAwayTeamPoints()
                                - m.getHomeTeamPoints());
    
                if (m.getHomeTeamPoints() > m.getAwayTeamPoints()) { // home team wins
                    giveWinnerAndLoserPoints(homeTeam, awayTeam);
                } else { // away team wins
                    giveWinnerAndLoserPoints(awayTeam, homeTeam);
                }

                m.setUpdated(true);
            }
        }
        return true;
    }

    private void giveWinnerAndLoserPoints(Team winner, Team loser) {
        winner.setWins(winner.getWins() + 1);
        loser.setLosses(loser.getLosses() + 1);
        winner.setSwissPoints(winner.getSwissPoints() + WIN_POINTS);
        loser.setSwissPoints(loser.getSwissPoints() + LOST_POINTS);
    }

    @Override
    public MatchingAlgorithmName getMatchingAlgorithmName() {
        return MatchingAlgorithmName.KNOCKOUT;
    }

    /***
     * Sort for swiss point and goal difference
     */
    private class StandingOrder implements Comparator<Team> {
        @Override
        public int compare(Team team1, Team team2) {
            int swissPointComparation = Double.compare(team1.getSwissPoints(), team1.getSwissPoints());
            if (swissPointComparation != 0) {
                return swissPointComparation;
            }
            return Integer.compare(team1.getGoalDifference(), team2.getGoalDifference());
        }
    }

}
