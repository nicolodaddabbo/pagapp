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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getFields() {
        return fields;
    }

    public void setFields(int fields) {
        this.fields = fields;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    
}
