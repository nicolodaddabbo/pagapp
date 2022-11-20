package it.paganello.pagapp.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Entity
public class Match {
    private @Id @GeneratedValue Long id;    
    private String field;
    private boolean played;
    private int resultTeam1;
    private int resultTeam2;
    private Team team1;
    private Team team2;
}
