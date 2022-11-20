package it.paganello.pagapp.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Match {
    private @Id @GeneratedValue Long id;    
    private String field;
    private boolean played;
}
