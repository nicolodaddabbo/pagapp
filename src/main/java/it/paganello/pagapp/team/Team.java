package it.paganello.pagapp.team;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.paganello.pagapp.match.Match;
import it.paganello.pagapp.tournament.Tournament;

@Entity
@Table(name = "TEA_TEAM")
public class Team {
    private @Id @GeneratedValue Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "WINS")
    private int wins;
    @Column(name = "LOSSES")
    private int losses;
    @Column(name = "POINTS")
    private double points;
    @Column(name = "GOAL_DIFFERENCE")
    private int goalDifference;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Match> awayMatches;

    @ManyToOne
    @JoinColumn(name = "TRN_ID")
    private Tournament tournament;

    public Team(final String name) {
        this.name = name;
    }
}
