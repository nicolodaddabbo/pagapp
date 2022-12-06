package it.paganello.pagapp.round;

import java.util.List;

import it.paganello.pagapp.match.MatchDto;
import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithmName;

public class RoundDto {
    private Long id;
    private String tournamentName;
    private int roundNumber;
    private boolean finished;
    private MatchingAlgorithmName matchingAlgorithmName;
    private List<MatchDto> matches;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTournamentName() {
        return tournamentName;
    }
    public void setTournament(String tournamentName) {
        this.tournamentName = tournamentName;
    }
    public int getRoundNumber() {
        return roundNumber;
    }
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public MatchingAlgorithmName getMatchingAlgorithmName() {
        return matchingAlgorithmName;
    }
    public void setMatchingAlgorithmName(MatchingAlgorithmName matchingAlgorithmName) {
        this.matchingAlgorithmName = matchingAlgorithmName;
    }
    public List<MatchDto> getMatches() {
        return matches;
    }
    public void setMatches(List<MatchDto> matches) {
        this.matches = matches;
    }
    
}
