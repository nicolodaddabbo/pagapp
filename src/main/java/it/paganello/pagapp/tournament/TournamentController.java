package it.paganello.pagapp.tournament;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paganello.pagapp.round.Round;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/tournaments")
public class TournamentController {
    @Autowired
    TournamentService service;

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return service.createTournament(tournament); 
    }

    @GetMapping
    public List<Tournament> getTournaments() {
        return service.getTournaments();
    }
    
    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return service.getTournamentById(id);
    }

    @GetMapping("/{id}/currentRound")
    public Optional<Round> getCurrentRoundByTournamentId(@PathVariable Long id) {
        return service.getCurrentRoundByTournamentId(id);
    }

    @GetMapping("/{id}/rounds/{roundNumber}")
    public Optional<Round> getRoundByRoundNumber(@PathVariable Long id, @PathVariable int roundNumber) {
        return service.getRoundByRoundNumber(id, roundNumber);
    }

    @GetMapping("/{id}/isCurrentRoundFinished")
    public boolean isCurrentRoundFinished(@PathVariable Long id) {
        return service.isCurrentRoundFinished(id);
    }
    

}
