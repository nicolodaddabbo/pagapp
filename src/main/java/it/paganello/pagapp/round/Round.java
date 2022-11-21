package it.paganello.pagapp.round;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import it.paganello.pagapp.match.Match;
import it.paganello.pagapp.tournament.Tournament;

@Entity
@Table(name = "ROU_ROUND")
public class Round {
    private @Id @GeneratedValue Long id;

    @Column(name = "NUMBER")
    private int roundNumber;
    @Column(name = "FINISHED")
    private boolean finished;
    @Transient
    private int fields;

    @ManyToOne
    @JoinColumn(name = "TRN_ID")
    private Tournament tournament;
    
    @OneToMany(mappedBy = "round")
    private Set<Match> matches;
}
