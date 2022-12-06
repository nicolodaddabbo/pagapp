package it.paganello.pagapp.match;

public class MatchDto {
    private Long id;

    private String field;
    private boolean finished;
    private int homeTeamPoints;
    private int awayTeamPoints;
    private String homeTeamName;
    private String awayTeamName;
    private int roundNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getField() {
        return field;
    }
    
    public void setField(String field) {
        this.field = field;
    }
    
    public boolean isFinished() {
        return finished;
    }
    
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
    public int getHomeTeamPoints() {
        return homeTeamPoints;
    }
    
    public void setHomeTeamPoints(int homeTeamPoints) {
        this.homeTeamPoints = homeTeamPoints;
    }
    
    public int getAwayTeamPoints() {
        return awayTeamPoints;
    }
    
    public void setAwayTeamPoints(int awayTeamPoints) {
        this.awayTeamPoints = awayTeamPoints;
    }
    
    public String getHomeTeamName() {
        return homeTeamName;
    }
    
    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }
    
    public String getAwayTeamName() {
        return awayTeamName;
    }
    
    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }
    
    public int getRoundNumber() {
        return roundNumber;
    }
    
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

}
