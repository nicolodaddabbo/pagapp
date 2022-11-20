package it.paganello.pagapp.round;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import it.paganello.pagapp.match.Match;
import it.paganello.pagapp.tournament.Tournament;

@Entity
public class Round {
    private @Id @GeneratedValue Long id;
    private int roundNumber;
    private boolean played;
    private int fields;
    private List<Match> matches;
}
