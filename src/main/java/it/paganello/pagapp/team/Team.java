package it.paganello.pagapp.team;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import it.paganello.pagapp.match.Match;

@Entity
public class Team {
    private @Id @GeneratedValue Long id;    
    private String name;
    private int wins;
    private int losses;
    private double points;
    private int goalDifference;
    private List<Match> matches;
}
