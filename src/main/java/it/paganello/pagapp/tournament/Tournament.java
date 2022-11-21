package it.paganello.pagapp.tournament;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import it.paganello.pagapp.matchingAlgorithm.MatchingAlgorithm;
import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.team.Team;

@Entity
@Table(name = "TRN_TOURNAMENT")
public class Tournament {
    private @Id @GeneratedValue Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CURRENT_RND")
    private int currentRoundNumber;

    @OneToMany(mappedBy = "tournament")
    private Set<Team> teams;
    @OneToMany(mappedBy = "tournament")
    private Set<Round> rounds;
    @Transient
    private Set<MatchingAlgorithm> matchingAlgorithms;
}
