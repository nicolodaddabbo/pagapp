package it.paganello.pagapp.match;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Entity
@Table(name="MTC_MATCH")
public class Match {
    private @Id @GeneratedValue Long id;

    @Column(name = "FIELD")
    private String field;
    @Column(name = "FINISHED")
    private boolean finished;
    @Column(name = "HOME_TEA_POINTS")
    private int homeTeamPoints;
    @Column(name = "AWAY_TEAM_POINTS")
    private int awayTeamPoints;

    @ManyToOne
    @JoinColumn(name = "HOME_TEA_ID")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "AWAY_TEA_ID")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "ROU_ID")
    private Round round;

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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    
}
