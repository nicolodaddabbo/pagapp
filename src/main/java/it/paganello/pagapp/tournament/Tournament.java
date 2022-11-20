package it.paganello.pagapp.tournament;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tournament {
    private @Id @GeneratedValue Long id;
    private String name;
    private int currentRoundNumber;
}
