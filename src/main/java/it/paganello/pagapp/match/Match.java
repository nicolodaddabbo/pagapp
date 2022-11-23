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
}