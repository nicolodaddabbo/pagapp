package it.paganello.pagapp.Round;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Round {
    private @Id @GeneratedValue Long id;
    private int roundNumber;
    private boolean played;
    private int fields;
}
